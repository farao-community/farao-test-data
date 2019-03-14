import com.powsybl.commons.datasource.*
import com.powsybl.iidm.import_.Importers

import java.nio.file.Paths

dataBaseName = args[0]
networkFile = args[1]
cracFile = args[2]

networkFilePath = Paths.get(networkFile)
cracFilePath = Paths.get(cracFile)

// Create two folders
folder1 = afs.getRootFolder(dataBaseName).createFolder("Folder 1")
folder2 = afs.getRootFolder(dataBaseName).createFolder("Folder 2")
folder2.description = "Only folder containing projects"

// Create three projects in folder 2
project1 = folder2.createProject("Folder structured project")
project1.description = "Each type of data in a different subfolder"
project2 = folder2.createProject("All data in subfolder project")
project2.description = "All data in a unique subfolder"
project3 = folder2.createProject("Empty project")
project3.description = "Empty project"

// Fill first project
folderNetwork = project1.rootFolder.createFolder("Network")
folderCrac = project1.rootFolder.createFolder("CRAC")
folderRAO = project1.rootFolder.createFolder("Computation").createFolder("RAO")

importedCase = folderNetwork.importedCaseBuilder().withFile(networkFilePath).build()
importedCracFile = folderCrac.importedCracFileBuilder().withName(DataSourceUtil.getBaseName(cracFilePath))
        .withDataSource(Importers.createDataSource(cracFilePath))
        .build()
folderRAO.RaoRunnerBuilder()
    .withName("RAO runner")
    .withCase(importedCase)
    .withCracFileProvider(importedCracFile)
    .build()

//fill second project
folderData = project2.rootFolder.createFolder("Data")

importedCase = folderData.importedCaseBuilder().withFile(networkFilePath).build()
importedCracFile = folderData.importedCracFileBuilder().withName(DataSourceUtil.getBaseName(cracFilePath))
        .withDataSource(Importers.createDataSource(cracFilePath))
        .build()
folderData.RaoRunnerBuilder()
    .withName("RAO runner")
    .withCase(importedCase)
    .withCracFileProvider(importedCracFile)
    .build()
