package com.adven.concordion.extensions.exam.db.commands

import com.adven.concordion.extensions.exam.core.html.html
import com.adven.concordion.extensions.exam.db.DbPlugin
import com.adven.concordion.extensions.exam.db.DbTester
import com.adven.concordion.extensions.exam.db.builder.ExamDataSet
import com.adven.concordion.extensions.exam.db.builder.SeedStrategy
import org.concordion.api.CommandCall
import org.concordion.api.Evaluator
import org.concordion.api.Fixture
import org.concordion.api.ResultRecorder

class DBSetCommand(
    name: String,
    tag: String,
    dbTester: DbTester,
    pattern: DbPlugin.ValuePrinter,
    private val allowedSeedStrategies: List<SeedStrategy>
) : DBCommand(name, tag, dbTester, pattern) {

    override fun setUp(cmd: CommandCall?, eval: Evaluator?, resultRecorder: ResultRecorder?, fixture: Fixture) {
        super.setUp(cmd, eval, resultRecorder, fixture)
        val el = cmd.html()
        el(renderTable(el.takeAwayAttr("caption"), expectedTable, remarks, valuePrinter))
        cmd.allowedOperation(allowedSeedStrategies)
            .execute(dbTester.connectionFor(ds), ExamDataSet(expectedTable, eval!!))
    }
}
