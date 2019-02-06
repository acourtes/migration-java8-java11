package fr.arolla.greenmove.human;

import fr.arolla.greenmove.Locomotion;
import fr.arolla.greenmove.LocomotionBatteryState;
import fr.arolla.greenmove.LocomotionState;
import fr.arolla.greenmove.Scooter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static fr.arolla.Randomizer.randomizer;
import static org.assertj.core.api.Assertions.assertThat;

public class JuicerTest {

    private Juicer juicer;

    @Before
    public void setUp() {
        juicer = randomizer.nextObject(Juicer.class);
        juicer.getProvider().setProviderName(juicer.getProviderWorkFor());
    }

    @Test
    public void a_juicer_can_just_have_scooters_with_him() {
        for (int i = 0; i < 3; i++) {
            List<Locomotion> locomotionsToTakeOf = juicer.askProviderListOfLocomotionsToJuice();

            locomotionsToTakeOf.forEach(pl -> assertThat(pl).isInstanceOf(Scooter.class));
        }
    }

    @Test
    public void a_juicer_can_just_have_10_scooters_max_with_him() {
        for (int i = 0; i < 3; i++) {
            List<Locomotion> locomotionsToTakeOf = juicer.askProviderListOfLocomotionsToJuice();

            assertThat(locomotionsToTakeOf).hasSize(10);
        }
    }

    @Test
    public void a_juicer_can_just_have_scooters_which_have_half_or_lower_battery_level() {
        for (int i = 0; i < 3; i++) {
            List<Locomotion> locomotionsToTakeOf = juicer.askProviderListOfLocomotionsToJuice();

            locomotionsToTakeOf.forEach(pl -> assertThat(pl.getBatteryState()).isNotEqualTo(LocomotionBatteryState.FULL));
        }
    }

    @Test
    public void a_juicer_can_just_have_scooters_from_his_provider() {
        for (int i = 0; i < 3; i++) {
            List<Locomotion> locomotionsToTakeOf = juicer.askProviderListOfLocomotionsToJuice();

            locomotionsToTakeOf.forEach(pl -> assertThat(pl.getProvider()).isEqualTo(juicer.getProviderWorkFor()));
        }
    }

    @Test
    public void a_juicer_can_just_have_scooters_in_excellent_and_good_state() {
        for (int i = 0; i < 3; i++) {
            List<Locomotion> locomotionsToTakeOf = juicer.askProviderListOfLocomotionsToJuice();

            locomotionsToTakeOf.forEach(pl -> assertThat(pl.getState()).isIn(LocomotionState.EXCELLENT, LocomotionState.GOOD));
        }
    }

    @Test
    public void a_juicer_can_just_have_scooters_which_are_not_rented() {
        for (int i = 0; i < 3; i++) {
            List<Locomotion> locomotionsToTakeOf = juicer.askProviderListOfLocomotionsToJuice();

            locomotionsToTakeOf.forEach(pl -> assertThat(pl.isRented()).isFalse());
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void a_juicer_can_just_have_a_fixed_scooters_list() {
        List<Locomotion> locomotionsToTakeOf = juicer.askProviderListOfLocomotionsToJuice();

        locomotionsToTakeOf.add(new Scooter());
    }

    @Test
    public void a_juicer_with_fast_method_just_gets_the_first_scooters_with_low_battery() {
        juicer.setProvider(new OptimisticProvider());

        List<Locomotion> scootersToJuice = juicer.getOptimisticQuicklyScootersToJuice();

        assertThat(scootersToJuice).hasSize(3);
    }

    private class OptimisticProvider extends Provider {
        @Override
        List<Locomotion> getListOfScootersToTakeOf() {
            List<Locomotion> scooters = new ArrayList<>();
            scooters.add(new Scooter().setBatteryState(LocomotionBatteryState.LOW));
            scooters.add(new Scooter().setBatteryState(LocomotionBatteryState.LOW));
            scooters.add(new Scooter().setBatteryState(LocomotionBatteryState.LOW));
            scooters.add(new Scooter().setBatteryState(LocomotionBatteryState.HALF));
            scooters.add(new Scooter().setBatteryState(LocomotionBatteryState.LOW));
            scooters.add(new Scooter().setBatteryState(LocomotionBatteryState.LOW));

            return scooters;
        }
    }

    @Test
    public void a_juicer_with_fast_method_just_gets_the_last_scooters_with_not_full_battery() {
        juicer.setProvider(new PessimisticProvider());

        List<Locomotion> scootersToJuice = juicer.getPessimisticQuicklyScootersToJuice();

        assertThat(scootersToJuice).hasSize(3);
    }

    private class PessimisticProvider extends Provider {
        @Override
        List<Locomotion> getListOfScootersToTakeOf() {
            List<Locomotion> scooters = new ArrayList<>();
            scooters.add(new Scooter().setBatteryState(LocomotionBatteryState.FULL));
            scooters.add(new Scooter().setBatteryState(LocomotionBatteryState.FULL));
            scooters.add(new Scooter().setBatteryState(LocomotionBatteryState.FULL));
            scooters.add(new Scooter().setBatteryState(LocomotionBatteryState.HALF));
            scooters.add(new Scooter().setBatteryState(LocomotionBatteryState.LOW));
            scooters.add(new Scooter().setBatteryState(LocomotionBatteryState.LOW));

            return scooters;
        }
    }

}