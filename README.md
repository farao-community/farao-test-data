# FARAO - data examples

For detailed information about FARAO toolbox, please refer to the [documentation website](https://farao-community.github.io/docs/)

## Provided data

This repository containes example data to be used with FARAO application.

### Network files examples

The [*network-files*](./network-files) directory contains network examples that can be imported in FARAO GSE.
- **testNetwork.xiidm**: Three nodes network in XIIDM format. It can be used as network input for [closed RAO](./closed-ra-optimisation-test) testing.
- **20170215_0830_2d4_uc1.uct**: Twenty nodes network in UCTE-DEF format.
- **MiniGridTestCGMES.zip**: Eleven nodes network in CGMES format. Initially provided by [ENTSO-E](https://www.entsoe.eu/).


### CRAC files examples

The [*crac-files*](./crac-files) directory contains CRAC examples that can be imported in FARAO GSE.
- **testCracRdPst.json**: Small CRAC file in JSON format.
- **20170215_xlsx_crac_fr_v08_v2.3.xlsx**: CRAC file in XLSX format. It contains the description of twenty four internal CRAC models.


### Network modification script examples

The [*network-modification-scripts*](./network-modification-scripts) directory contains modification scripts that can be used in FARAO GSE.
- **pstBeNeutralTap.groovy**: Script that sets phase shifter of id "**PST_BE**" to neutral tap position.
- **allBelgianPstNeutralTap.groovy**: Script that sets all phase shifters in belgian substations to neutral tap position.
- **allPstNeutralTap.groovy**: Script that sets all phase shifters to neutral tap position.


### Closed RAO examples

The [*closed-ra-optimisation-test*](./closed-ra-optimisation-test) directory contains data that can be used as example to illustrate the closed RAO module.
- **testNetwork.xiidm**: Input three nodes network.
- **testCracPstOnly.json**: Test CRAC file with only one PST remedial action available.
- **testCracRdOnly.json**: Test CRAC file with only two redispatch remedial actions available.
- **testCracRdPst.json**: Test CRAC file with both PST and redispatch remedial actions available.


### [Flow decomposition examples](./flow-decomposition-test)

- **20140116_0830_2D4_UX1.uct**: Input twenty nodes network with four countries.
- **cracData.json**: Associated CRAC file for flow decomposition.


### [Groovy script examples](./script-examples)

The [*script-examples*](./script-examples) directory contains groovy script examples that can be run using **itools** binary.
- **SimpleTestCase.groovy**: **itools** script that generates the test case used as [closed RAO example](./closed-ra-optimisation-test).
- **NetworkMergingTestScript.groovy**: **itools** script that merges three IGMs and saves the result in database.
- **DataBaseInitialisation.groovy**: **itools** script that initialises AFS database with example projects and data.
- **ProcessAutomation.groovy**: **itools** script that automates the process of creating a closed RAO study in AFS database and running the computation.

