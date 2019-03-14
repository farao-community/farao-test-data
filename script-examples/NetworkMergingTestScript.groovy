import com.powsybl.iidm.import_.Importers
import com.powsybl.iidm.export.Exporters
import com.powsybl.iidm.network.NetworkFactory
import java.nio.file.Paths

dataBaseName = args[0]
projectName = args[1]
networkFile1 = args[2]
networkFile2 = args[3]
networkFile3 = args[4]

// Create project
project = afs.getRootFolder(dataBaseName).createProject(projectName)
root = project.getRootFolder()

// Import networks
network1 = Importers.loadNetwork(networkFile1)
network2 = Importers.loadNetwork(networkFile2)
network3 = Importers.loadNetwork(networkFile3)

// Merge networks
mergedNetwork = NetworkFactory.create("MergedNetwork", "script")
mergedNetwork.merge(network1, network2, network3)
results = loadFlow(mergedNetwork)

if (results.ok) {
  // Save merged network in database
  importedMergedCase = root.importedCaseBuilder().withNetwork(mergedNetwork).build()
}


