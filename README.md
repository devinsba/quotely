# quotely

## Next Steps

When I ran out of time I was in the middle of making `ForismaticService` testable. By requesting the base URL from the environment I would be able to use something like [`MockWebServer`](https://github.com/square/okhttp/tree/master/mockwebserver) to validate the request being made.

I would also consider using the JSON format from the API, which would require adding a JSON parsing library, such as Jackson. As is I like my project not requiring any dependencies outside the standard library at runtime, given that the API outputs a reasonable value for output.

## Requirements

* Java 11+

## Building

```shell
./gradlew installDist
```

### Running

```shell
./build/install/quotely/bin/quotely <lang>
```

## Testing

```shell
./gradlew test
```