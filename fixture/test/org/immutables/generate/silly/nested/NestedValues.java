package org.immutables.generate.silly.nested;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import org.immutables.common.marshal.Marshaler;
import org.immutables.common.marshal.Marshaling;
import org.immutables.generate.silly.nested.ImmutableGroupedClasses.NestedOne;
import org.immutables.generate.silly.nested.ImmutableInnerNested.Inner;
import org.immutables.generate.silly.nested.ImmutableInnerNested.Nested;
import org.junit.Ignore;
import org.junit.Test;
import static org.immutables.check.Checkers.*;

public class NestedValues {

  final JsonFactory jsonFactory = new JsonFactory()
      .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES)
      .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
      .disable(JsonGenerator.Feature.QUOTE_FIELD_NAMES);

  @Test
  public void nestedGroupingCompilation() {
    NestedOne one = ImmutableGroupedClasses.NestedOne.builder().build();
    check(one).notNull();
    ImmutableInnerNested outer = ImmutableInnerNested.builder().build();
    check(outer).notNull();
    Inner inner = Inner.builder().build();
    check(inner).notNull();
    Nested nested = Nested.builder().build();
    check(nested).notNull();
  }

  @Test
  public void nonNestedGroupingCompilation() {
    NonGrouped.Abra a = ImmutableAbra.builder().build();
    check(a).notNull();
    NonGrouped.Cadabra c = ImmutableCadabra.builder().build();
    check(c).notNull();
  }

  @Ignore
  @Test
  public void marshalingOfNested() {
    Marshaler<GroupedClasses.NestedOne> marshaler =
        Marshaling.marshalerFor(GroupedClasses.NestedOne.class);

    check(Marshaling.toJson(ImmutableGroupedClasses.NestedOne.builder().build())).is("{}");
  }
}
