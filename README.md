# SIVANNSAN MilliData
A Java module manipulating data alternative to Json API,
yet the data are structured in the subset of Json data

### Core classes
+ MilliData (abstract)
+ MilliNull (`null`)
+ MilliValue (`""`)
+ MilliList (`[]`)
+ MilliMap (`{}`)

### Other classes
+ MilliDataParser (`MilliData data = MilliDataParser.parse(string);`)
+ MilliDataConverter (`MilliData data = MilliDataConverter(object);`)

### Requires
+ [SIVANNSAN Foundation](https://github.com/sivannsan/foundation)

### Maven
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```xml
<dependency>
    <groupId>com.github.sivannsan</groupId>
    <artifactId>millidata</artifactId>
    <version>1.2.0</version>
</dependency>
```
