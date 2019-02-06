package fr.arolla.optional;

import fr.arolla.greenmove.LocomotionProvider;
import fr.arolla.greenmove.human.Provider;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayWithOptionalTest {

    @Test
    public void return_a_non_empty_optional_if_the_one_we_got_is_empty() {
        Random random = new Random();
        boolean isEven = random.nextInt() % 2 == 0;
        Optional<String> myOptional = isEven ? Optional.empty() : Optional.of("Toto");
        Optional<String> finalOptional;

        // TODO Use a Java 9 feature to simplify that
        if (myOptional.isPresent()) {
            finalOptional = myOptional;
        } else {
            finalOptional = Optional.of("Fail");
        }

        if (isEven) {
            assertThat(finalOptional.get()).isEqualTo("Fail");
        } else {
            assertThat(finalOptional.get()).isEqualTo("Toto");
        }
    }

    @Test
    public void stream_an_optional() {
        Provider provider = new Provider().setProviderName(LocomotionProvider.BIRD);
        Stream<Optional<Provider>> optionalStream = Stream.of(Optional.of(provider));

        // TODO use a Java 9 feature to simplify that
        List<LocomotionProvider> locomotionProviders = optionalStream.filter(Optional::isPresent)
                .map(Optional::get)
                .map(Provider::getProviderName)
                .collect(Collectors.toList());

        assertThat(locomotionProviders).hasSize(1);
        assertThat(locomotionProviders).contains(LocomotionProvider.BIRD);
    }
}
