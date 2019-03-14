import com.powsybl.commons.datasource.*
import com.powsybl.iidm.import_.Importers
import com.farao_community.farao.ra_optimisation.json.JsonRaoComputationResult

import java.nio.file.Paths

dataBaseName = args[0]
projectName = args[1]
cracFile = args[2]
networkFile = args[3]
outputFile = args[4]
networkFilePath = Paths.get(networkFile)
cracFilePath = Paths.get(cracFile)
outputFilePath = Paths.get(outputFile)

// Creates project
project = afs.getRootFolder(dataBaseName).createProject(projectName)
root = project.getRootFolder()

// Imports CRAC file
importedCracFile = root.importedCracFileBuilder()
        .withName(DataSourceUtil.getBaseName(cracFilePath))
        .withDataSource(Importers.createDataSource(cracFilePath))
        .build()

// Imports network
importedCase = root.importedCaseBuilder().withFile(networkFilePath).build()

// Creates rao computation runner
raoComputationRunner = root.RaoRunnerBuilder()
    .withName("RAO runner")
    .withCase(importedCase)
    .withCracFileProvider(importedCracFile)
    .build()

// Run rao computation simulation
raoComputationRunner.run()

// Export results
results = raoComputationRunner.readResult()
JsonRaoComputationResult.write(results, outputFilePath)

