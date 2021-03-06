package com.farao_community.farao.closed_optimisation_rao.groovy

import com.farao_community.farao.data.crac_file.CracFile
import com.farao_community.farao.data.crac_file.MonitoredBranch
import com.farao_community.farao.data.crac_file.PreContingency
import com.farao_community.farao.data.crac_file.PstElement
import com.farao_community.farao.data.crac_file.RedispatchRemedialActionElement
import com.farao_community.farao.data.crac_file.RemedialAction
import com.farao_community.farao.data.crac_file.TypeOfLimit
import com.farao_community.farao.data.crac_file.UsageRule
import com.farao_community.farao.data.crac_file.json.JsonCracFile
import com.powsybl.iidm.export.Exporters
import com.powsybl.iidm.network.Country
import com.powsybl.iidm.network.NetworkFactory
import com.powsybl.iidm.network.TopologyKind

import java.nio.file.Paths

outputNetwork = args[0]
outputCrac = args[1]

network = NetworkFactory.create("TestNetwork", "code")
substationFr = network.newSubstation()
        .setId("SUBSTATION_FR")
        .setName("French substation")
        .setCountry(Country.FR)
        .add()
voltageLevelFr = substationFr.newVoltageLevel()
        .setId("VOLTAGE_LEVEL_FR")
        .setName("French voltage level")
        .setNominalV(400)
        .setTopologyKind(TopologyKind.BUS_BREAKER)
        .add()
busFr = voltageLevelFr.getBusBreakerView().newBus()
        .setId("BUS_FR")
        .setName("French bus")
        .add()
generatorFr = voltageLevelFr.newGenerator()
        .setId("GENERATOR_FR")
        .setName("French generator")
        .setTargetP(3000)
        .setTargetV(400)
        .setVoltageRegulatorOn(true)
        .setMinP(0)
        .setMaxP(5000)
        .setBus(busFr.id)
        .add()
voltageLevelFr.newLoad()
        .setId("LOAD_FR")
        .setName("French load")
        .setP0(1800)
        .setQ0(0)
        .setBus(busFr.id)
        .add()

substationBe = network.newSubstation()
        .setId("SUBSTATION_BE")
        .setName("Belgian substation")
        .setCountry(Country.BE)
        .add()
voltageLevelBe = substationBe.newVoltageLevel()
        .setId("VOLTAGE_LEVEL_BE")
        .setName("Belgian voltage level")
        .setNominalV(400)
        .setTopologyKind(TopologyKind.BUS_BREAKER)
        .add()
busBe1 = voltageLevelBe.getBusBreakerView().newBus()
        .setId("BUS_BE_1")
        .setName("Belgian bus n°1")
        .add()
busBe2 = voltageLevelBe.getBusBreakerView().newBus()
        .setId("BUS_BE_2")
        .setName("Belgian bus n°2")
        .add()
generatorBe = voltageLevelBe.newGenerator()
        .setId("GENERATOR_BE")
        .setName("Belgian generator")
        .setTargetP(1000)
        .setTargetV(400)
        .setVoltageRegulatorOn(true)
        .setMinP(0)
        .setMaxP(5000)
        .setBus(busBe1.id)
        .add()
voltageLevelBe.newLoad()
        .setId("LOAD_BE")
        .setName("Belgian load")
        .setP0(2200)
        .setQ0(0)
        .setBus(busBe1.id)
        .add()
pstBe = substationBe.newTwoWindingsTransformer()
        .setId("PST_BE")
        .setName("Belgian phase shift transformer")
        .setR(0)
        .setX(5)
        .setG(0)
        .setB(0)
        .setRatedU1(400)
        .setRatedU2(400)
        .setVoltageLevel1(voltageLevelBe.id)
        .setVoltageLevel2(voltageLevelBe.id)
        .setBus1(busBe1.id)
        .setBus2(busBe2.id)
        .add()
pstBe.newPhaseTapChanger()
        .beginStep().setRho(1).setAlpha(-0.90).setR(0).setX(0).setG(0).setB(0).endStep()
        .beginStep().setRho(1).setAlpha(-0.75).setR(0).setX(0).setG(0).setB(0).endStep()
        .beginStep().setRho(1).setAlpha(-0.60).setR(0).setX(0).setG(0).setB(0).endStep()
        .beginStep().setRho(1).setAlpha(-0.45).setR(0).setX(0).setG(0).setB(0).endStep()
        .beginStep().setRho(1).setAlpha(-0.30).setR(0).setX(0).setG(0).setB(0).endStep()
        .beginStep().setRho(1).setAlpha(-0.15).setR(0).setX(0).setG(0).setB(0).endStep()
        .beginStep().setRho(1).setAlpha(0).setR(0).setX(0).setG(0).setB(0).endStep()
        .beginStep().setRho(1).setAlpha(0.15).setR(0).setX(0).setG(0).setB(0).endStep()
        .beginStep().setRho(1).setAlpha(0.30).setR(0).setX(0).setG(0).setB(0).endStep()
        .beginStep().setRho(1).setAlpha(0.45).setR(0).setX(0).setG(0).setB(0).endStep()
        .beginStep().setRho(1).setAlpha(0.60).setR(0).setX(0).setG(0).setB(0).endStep()
        .beginStep().setRho(1).setAlpha(0.75).setR(0).setX(0).setG(0).setB(0).endStep()
        .beginStep().setRho(1).setAlpha(0.90).setR(0).setX(0).setG(0).setB(0).endStep()
        .setLowTapPosition(-6)
        .setTapPosition(-4)
        .add()
interconnection1 = network.newLine()
        .setId("FRANCE_BELGIUM_1")
        .setName("France-Belgium interconnection n°1")
        .setR(0)
        .setX(10)
        .setG1(0)
        .setG2(0)
        .setB1(0)
        .setB2(0)
        .setVoltageLevel1(voltageLevelBe.id)
        .setVoltageLevel2(voltageLevelFr.id)
        .setBus1(busBe1.id)
        .setBus2(busFr.id)
        .add()

interconnection1.newCurrentLimits1().setPermanentLimit(500 / 400 * 1000 / Math.sqrt(3)).add()
interconnection1.newCurrentLimits2().setPermanentLimit(500 / 400 * 1000 / Math.sqrt(3)).add()

interconnection2 = network.newLine()
        .setId("FRANCE_BELGIUM_2")
        .setName("France-Belgium interconnection n°2")
        .setR(0)
        .setX(5)
        .setG1(0)
        .setG2(0)
        .setB1(0)
        .setB2(0)
        .setVoltageLevel1(voltageLevelBe.id)
        .setVoltageLevel2(voltageLevelFr.id)
        .setBus1(busBe2.id)
        .setBus2(busFr.id)
        .add()

interconnection2.newCurrentLimits1().setPermanentLimit(500 / 400 * 1000 / Math.sqrt(3)).add()
interconnection2.newCurrentLimits2().setPermanentLimit(500 / 400 * 1000 / Math.sqrt(3)).add()

crac = CracFile.builder()
        .id("TEST_CRAC_FILE")
        .name("Test CRAC file")
        .sourceFormat("code")
        .preContingency(PreContingency.builder()
            .monitoredBranches(Arrays.asList(
                MonitoredBranch.builder().id("MONITORED_FRANCE_BELGIUM_1").name("France-Belgium interconnection n°1 monitored").branchId(interconnection1.id).fmax(500).build(),
                MonitoredBranch.builder().id("MONITORED_FRANCE_BELGIUM_2").name("France-Belgium interconnection n°2 monitored").branchId(interconnection2.id).fmax(500).build()))
            .build()
        )
        .remedialActions(Arrays.asList(
            RemedialAction.builder().id("PST_RA").name("Belgian PST remedial action")
                .remedialActionElements(Arrays.asList(PstElement.builder().id(pstBe.id).typeOfLimit(TypeOfLimit.ABSOLUTE).minStepRange(-6).maxStepRange(6).build()))
                .usageRules(Arrays.asList(UsageRule.builder().id("PST_FREE_TO_USE").instants(UsageRule.Instant.N).usage(UsageRule.Usage.FREE_TO_USE).build()))
            .build(),
            RemedialAction.builder().id("FRENCH_GENERATOR_RA").name("French generator remedial action")
                .remedialActionElements(Arrays.asList(RedispatchRemedialActionElement.builder().id(generatorFr.id).minimumPower(100).maximumPower(5000).startupCost(1000).marginalCost(45).build()))
                .usageRules(Arrays.asList(UsageRule.builder().id("FRENCH_GENERATOR_FREE_TO_USE").instants(UsageRule.Instant.N).usage(UsageRule.Usage.FREE_TO_USE).build()))
                .build(),
            RemedialAction.builder().id("BELGIAN_GENERATOR_RA").name("Belgian generator remedial action")
                .remedialActionElements(Arrays.asList(RedispatchRemedialActionElement.builder().id(generatorBe.id).minimumPower(100).maximumPower(5000).startupCost(1000).marginalCost(50).build()))
                .usageRules(Arrays.asList(UsageRule.builder().id("BELGIAN_GENERATOR_FREE_TO_USE").instants(UsageRule.Instant.N).usage(UsageRule.Usage.FREE_TO_USE).build()))
                .build()
            )
        )
        .build()

Exporters.export("XIIDM", network, null, Paths.get(outputNetwork))
JsonCracFile.write(crac, new FileOutputStream(outputCrac))