---
name: seedu-java-coding-standard
description: 'Apply the SE-EDU Java basic and intermediate coding standard to this project. Use when writing, reviewing, or refactoring Java code, including naming, layout, imports, packages, variables, control flow, and Javadocs.'
argument-hint: '[optional file or package to review]'
user-invocable: true
disable-model-invocation: false
---

# SE-EDU Java Coding Standard

Follow the SE-EDU Java basic and intermediate conventions from https://se-education.org/guides/conventions/java/intermediate.html for all Java code in this project.

## Required Rules

- Keep `src/main/java` as the source root; package declarations must match the directory path.
- Use lower-case package names, PascalCase class names, camelCase method and variable names, and SCREAMING_SNAKE_CASE constants.
- Name methods as verbs and boolean values with boolean-sounding names such as `isDone`, `hasData`, or `canExecute`.
- Use four spaces for indentation and K&R braces. Keep lines at or below 120 characters, preferably below 110.
- Use explicit imports, keep imports minimal, and order imports consistently: static imports, Java/JDK imports, third-party imports, then project imports.
- Initialize variables at declaration where practical and keep them in the smallest useful scope.
- Always use braces for loops and conditionals, including single-statement bodies.
- Separate logical blocks with one blank line and use whitespace around operators and after commas.
- Add descriptive Javadocs to public classes and public methods. Include useful `@param`, `@return`, and `@throws` tags; getters, setters, and exact overrides may inherit documentation when their behavior is unchanged.
- Write comments in English using American spelling. Keep comments focused on intent and behavior.
- Use test method names in the form `featureUnderTest_testScenario_expectedBehavior()` when a descriptive name needs multiple parts.

## Review Procedure

1. Check package declarations and source-root-relative paths.
2. Check naming, indentation, braces, whitespace, line length, and import ordering.
3. Check public API Javadocs and their tags.
4. Run the project’s Javadoc task and relevant tests after edits.
