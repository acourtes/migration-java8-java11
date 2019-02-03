package fr.arolla.greenmove.human;

import fr.arolla.greenmove.Locomotion;
import fr.arolla.greenmove.LocomotionCategory;
import fr.arolla.greenmove.Scooter;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static fr.arolla.Randomizer.randomizer;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = randomizer.nextObject(User.class).setUsingALocomotion(false);
    }

    @Test
    public void a_user_can_access_a_locomotion_if_it_is_public() {
        for (int i = 0; i < 10; i++) {
            final Locomotion locomotion = randomizer.nextObject(Locomotion.class);

            Optional<Locomotion> optionalLocomotion = user.tryAccessToLocomotion(locomotion);

            optionalLocomotion.ifPresent(l ->
                    assertThat(optionalLocomotion.get().getCategory())
                            .isIn(LocomotionCategory.SCOOTER, LocomotionCategory.PUBLIC_BIKE));
        }
    }

    @Test
    public void a_user_can_access_a_locomotion_if_it_is_public_and_not_rented() {
        for (int i = 0; i < 10; i++) {
            final Locomotion locomotion = randomizer.nextObject(Locomotion.class);

            Optional<Locomotion> optionalLocomotion = user.tryAccessToLocomotion(locomotion);

            optionalLocomotion.ifPresent(l -> {
                assertThat(optionalLocomotion.get().isRented()).isFalse();
                assertThat(optionalLocomotion.get().isPublicLocomotion()).isTrue();
            });
        }
    }

    @Test
    public void a_user_accessing_a_locomotion_changes_state_and_get_scooter_id_in_return() {
        for (int i = 0; i < 10; i++) {
            final Locomotion locomotion = randomizer.nextObject(Locomotion.class);
            user.setUsingALocomotion(false);
            boolean isRentable = user.tryAccessToLocomotion(locomotion).isPresent();

            String locomotionId = user.getAPublicLocomotion(locomotion);

            if (isRentable) {
                assertThat(locomotion.getId()).isEqualTo(locomotionId);
                assertThat(user.isUsingALocomotion()).isTrue();
                assertThat(locomotion.isRented()).isTrue();
            } else {
                assertThat(locomotionId).isEqualTo(":-(");
                assertThat(user.isUsingALocomotion()).isFalse();
                assertThat(user.isHappy()).isFalse();
                if (locomotion.isPublicLocomotion()) {
                    assertThat(locomotion.isRented()).isTrue();
                }
            }
        }
    }

    @Test
    public void test_successful_scooter_rental_process() {
        user.setProvider(new GoodProvider());
        String scooterId = user.getAScooter();

        assertThat(scooterId).isNotBlank();
        assertThat(scooterId).isEqualTo("ID");
        assertThat(user.isUsingALocomotion()).isTrue();
    }

    @Test
    public void test_unsuccessful_scooter_rental_process() {
        user.setProvider(new BadProvider());
        String scooterId = user.getAScooter();

        assertThat(scooterId).isEqualTo(":-(");
        assertThat(user.isUsingALocomotion()).isFalse();
        assertThat(user.isHappy()).isFalse();
    }

    class GoodProvider extends Provider {
        @Override
        Optional<Locomotion> getAScooterToRent() {
            return Optional.of(new Scooter().setRented(false)
                    .setId("ID"));
        }
    }

    class BadProvider extends Provider {
        @Override
        Optional<Locomotion> getAScooterToRent() {
            return Optional.empty();
        }
    }

}