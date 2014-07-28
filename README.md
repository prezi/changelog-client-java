# changelog-client-java

A simple JVM client for a [Changelog](https://github.com/prezi/changelog) server. 

## Usage

TODO: how to add as dependency, once uploaded to Sonatype.

```Java
import com.prezi.changelog.ChangelogClient;
import java.io.IOException;

public class Example {
    public static void simple() throws IOException {
        ChangelogClient client = new ChangelogClient();
        client.send("test event");
        client.send("test-category", "event in category test-'category'");
        client.send(2, "event with criticality 2");
        client.send(4, "test-category", "fully parameterized event");
    }
}
```

## Configuration

The `ChangelogClient` constructor takes an instance of a class implementing the `ChangelogClientConfig` interface.
To configure the client, you can provide your own class, or use `DefaultChangelogClientConfig` which reads its
configuration from system properties. You can of course also extend the default client configuration and
just override whatever you need.

`ChangelogClientConfig` method|System property read by `DefaultChangelogClientConfig`|Valid values
------------------------------|------------------------------------------------------|------------
`getEndpoint`                 |`changelog.endpoint`                                  | A URL as string. Must contain the full URL to the Changelog API, for example: `https://changelog.mycompany.com/api/events`
`getCriticality`              |`changelog.criticality`, default 1                    | Integer: 1 <= N <= 5. The criticality to use when a criticality is not passed to the `send` method.
`getCategory`                 |`changelog.category`, default "misc"                  | Any string. The category to use when a category is not passed to the `send` method.
`getAuthProviderType`         |`changelog.auth.providerType`, default "NOOP"         | A value from the enum `ChangelogAuthProviderType`. A supported authentication method (see below).

## Authentication

If your changelog server is configured to require authentication, you can configure the client to send authentication
headers.

### If your authentication method is supported by this client library

You can set the method to use via the return value of the `getAuthProvider` method of the config object. That's the
`changelog.auth.providerType` system property when using the `DefaultChangelogClientConfig`. The value can be any of the
following:

#### NOOP

No authentication. This is the default.

#### HTTP_BASIC_AUTH

Basic HTTP authentication. Requires further configuration via the system properties `changelog.auth.httpBasic.username`
and `changelog.auth.httpBasic.password`.

## If your authentication method is NOT supported by this client library

I expect the authentication method to be useful for others as well, so a pull-request extending the list of supported
authentication methods is most welcome. If that's not an option or too much overhead, you can provide your own
authentication class implementing the `ChangelogAuthProvider` interface and passing an instance as the second argument
to the `ChangelogClient` constructor.

## Logging

The client and its helper classes log via SLF4J.

