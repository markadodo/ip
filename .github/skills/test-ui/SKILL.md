---
name: test-ui
description: 'Run scripted UI tests for the Dulio Java CLI. Use when given lists of commands and expected outputs, when verifying console interactions, or when checking a test/ui-test-plan.md test plan.'
argument-hint: '[optional test case names or plan path]'
user-invocable: true
disable-model-invocation: false
---

# Test UI

Run the command-line UI tests recorded in `test/ui-test-plan.md`.

## Procedure

1. Read `test/ui-test-plan.md` and identify the requested test cases. If the user supplied a command list and expected-output list, use those values for the session; otherwise run every case in the plan.
2. Confirm that each case has an aim, an ordered list of console inputs, and an ordered list of expected outputs.
3. Compile the Java program before testing:
   ```powershell
   javac -d build src\main\java\*.java
   ```
   If compilation fails, stop and report the compiler output.
4. For each test case, run the program from the repository root using its input list in order. Use a non-interactive standard-input pipeline, for example:
   ```powershell
   @('todo borrow book', 'list', 'bye') | java -cp build Dulio
   ```
5. Normalize only transport noise before comparison: convert CRLF to LF and trim trailing whitespace from each line. Do not remove application output, separators, blank lines, or prompt text.
6. Compare the normalized actual output with the normalized expected output for that case. The comparison must be exact and ordered.
7. Print a transcript for every executed case containing:
   - the case name and aim;
   - `INPUT>` followed by each console input line;
   - `OUTPUT>` followed by the complete console output;
   - the result (`PASS` or `FAIL`).
8. If a case fails, stop immediately. Report the complete actual output and complete expected output for that case, and do not run later cases.
9. If all cases pass, report the number of passed cases and retain the console transcripts in the response.

## Test Plan Format

Keep test cases in `test/ui-test-plan.md`. Each case must define the following sections:

```markdown
## Case: descriptive-name

### Aim
What behavior this case verifies.

### Inputs
1. `first console command`
2. `second console command`

### Expected output
1. `expected output line or exact output block`
2. `expected output line or exact output block`
```

For stable exact comparisons, prefer an `Expected output` fenced block containing the complete output after normalization. If a case uses an input list, the expected output must include the complete transcript produced by that input list, including separators and blank lines.

## Failure Reporting

Never continue after a failed case. Show both labeled blocks:

```text
ACTUAL OUTPUT
...

EXPECTED OUTPUT
...
```

Keep the failure report separate from the transcript so the mismatch is easy to inspect. Do not silently update the test plan to make a failing test pass.
