Frontend application uses HTMX and Tailwindcss.
If you want to run it for development, you should first run the application and then start 
`build:dev` script from `package.json` file. It will start Tailwind CLI with `--watch` flag enabled.

## IntelliJ IDEA

If you use IntelliJ IDEA, you can use provided configuration from `.run` directory.

## Other IDE

Compile and run your application, then run:

```shell
mvn node-and-npm frontend:npm@npm-install frontend:npm@npm-build-dev
```
