# Configuring Json Handling

Spin can be configured to change Json parsing, writing and mapping settings, for example to tolerate documents that are not strictly compliant to the standard. As Spin uses [Jackson][jackson-wiki] to handle json, most of Jackson's configuration options also apply to Spin.

Whenever the json data format is explicitly declared, i.e. by using `JSON(..., jsonTree())`
or `S(..., jsonTree())`, configuration to Jackson can be passed. This can be either done by using
a fluent API or by providing a map of configuration parameters.

## Examples:

Configuring the jackson parser to allow leading zeros for numbers when parsing and to disable quoting of property names when writing works as follows in Java:

```java
import static org.camunda.spin.Spin.*;

SpinJsonNode json = S("{\"age\": 042}", 
  jsonTree()
    .reader()
      .allowNumericLeadingZeros(true)
    .writer()
      .quoteFieldNames(false))
    .done();
    
json.writeToStream(..)
```

The data format can also be configured using a map, which comes in handy in scripting environments. The following
example uses Javascript:

```javascript
var customer = S('{"age": 042}', 
  jsonTree()
    .reader().config({ALLOW_NON_NUMERIC_NUMBERS: true})
    .writer().config({QUOTE_FIELD_NAMES: false})
    .mapper().config({USE_BIG_INTEGER_FOR_INTS: true})
    .done());
```

As a more concise equivalent, the function `JSON(obj, readerConfig, writerConfig, mapperConfig)` can be used:

```javascript
var customer = 
  JSON('{"age": 042}', 
    {ALLOW_NON_NUMERIC_NUMBERS: true}, 
    {QUOTE_FIELD_NAMES: false},
    {USE_BIG_INTEGER_FOR_INTS: true});
```

## Configuration Options

All of Jackson's [parsing][jackson-parser-features], [writing][jackson-generator-features], [serialization][jackson-serialization-features] and [deserialization][jackson-deserialization-features] configuration options are supported by Spin. When providing a configuration in form of a map, the keys are the same as the names of the Jackson enum values. In the example above, the key `ALLOW_NON_NUMERIC_NUMBERS` corresponds to the enum value `com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS`.

[jackson-wiki]: http://wiki.fasterxml.com/JacksonHome
[jackson-parser-features]: https://fasterxml.github.io/jackson-core/javadoc/2.4/com/fasterxml/jackson/core/JsonParser.Feature.html
[jackson-generator-features]: https://fasterxml.github.io/jackson-core/javadoc/2.4/com/fasterxml/jackson/core/JsonGenerator.Feature.html
[jackson-deserialization-features]: https://fasterxml.github.io/jackson-databind/javadoc/2.4/com/fasterxml/jackson/databind/DeserializationFeature.html
[jackson-serialization-features]: https://fasterxml.github.io/jackson-databind/javadoc/2.4/com/fasterxml/jackson/databind/SerializationFeature.html