![Build](https://github.com/thailb-developer/spring-tdd-boilerplate/actions/workflows/ci-pipeline.yml/badge.svg?branch=main)

## Spring Boot Boiler Plate

### Preface

The purpose of this boilerplate is providing a read-to-use code base of Spring Boot which packages with necessaries
such as Testcontainer, Log4J2 and some more.

### How to Use?

Clone it by using following command:

```
git@github.com:thailb-developer/spring-tdd-boilerplate.git
```

There are a placeholder for `Controller`, `Service` and `Repository` . Pleas feel free to remote them or use them as a
reference to develop your business requirements.

### Features

#### Enabled MongoDB

The boilerplate has been enabled `MongoDB` for development and testing. You could replace it to any production-ready
databases.

#### Enabled Testcontainer

`Testcontainer` is a new standard to write tests, included unit test and integration test. Instead of use the old `H2`,
with `Testcontainer`, the tests can simulate exactly the database structure of production. Hence, you can achieve:

Learn more about `Testcontainer` at: `https://www.testcontainers.org/`

* Ensure the database on test and production are the same.
* Close the gap between integration test and unit test. You don't need to mock the data while persisting anymore.

#### Async Log4J2 and JSON Layout

By default, Spring Boot used `Logback` as the default logger without any advanced configuration. The boilerplate
replace `Logback` by `Log4J2` - A modern logger module.

Additionally, the `Log4J2` also has been pre-configured for:

* Asynchronous Log to tune the performance.
* JSON Layout to be forwarded directly to any log database, such as Loki without require to write the parser at log
  forwarder level.

### Next Features

* [ ] Enable authentication and authorization with `Auth0`.
* [ ] Provide another build which pre-configure tu use `PostgreSQL`.

### Thanks

If this boilerplate provides usefulness to you. Please give it a star. My appreciate.
If you had any idea, please feel free to open a PR to discuss.