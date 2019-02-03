package fr.arolla.greenmove.human;

import fr.arolla.greenmove.Locomotion;
import fr.arolla.greenmove.LocomotionCategory;
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
    public void a_user_accessing_a_scooter_changes_state_and_get_scooter_id_in_return() {
        for (int i = 0; i < 10; i++) {
            final Locomotion locomotion = randomizer.nextObject(Locomotion.class);
            user.setUsingALocomotion(false);
            boolean isRentable = user.tryAccessToLocomotion(locomotion).isPresent();

            String scooterId = user.getAScooter(locomotion);

            if (locomotion.isAScooter() && isRentable) {
                assertThat(scooterId).isNotBlank();
                assertThat(locomotion.getId()).isEqualTo(scooterId);
                assertThat(user.isUsingALocomotion()).isTrue();
                assertThat(locomotion.isRented()).isTrue();
            } else {
                assertThat(scooterId).isEmpty();
                assertThat(user.isUsingALocomotion()).isFalse();
                if (locomotion.isAScooter()) {
                    assertThat(locomotion.isRented()).isTrue();
                }
            }

        }
    }
}