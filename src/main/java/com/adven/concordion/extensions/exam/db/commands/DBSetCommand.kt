package com.adven.concordion.extensions.exam.db.commands

import com.adven.concordion.extensions.exam.configurators.ExamDbTester
import com.adven.concordion.extensions.exam.html.Html
import com.adven.concordion.extensions.exam.html.html
import com.github.database.rider.core.api.dataset.SeedStrategy
import com.github.database.rider.core.api.dataset.SeedStrategy.CLEAN_INSERT
import com.github.database.rider.core.api.dataset.SeedStrategy.INSERT
import org.concordion.api.CommandCall
import org.concordion.api.Evaluator
import org.concordion.api.ResultRecorder
import org.dbunit.dataset.DefaultDataSet
import org.dbunit.dataset.ITable

class DBSetCommand(name: String, tag: String, dbTester: ExamDbTester) : DBCommand(name, tag, dbTester) {
    override fun setUp(cmd: CommandCall?, eval: Evaluator?, resultRecorder: ResultRecorder?) {
        super.setUp(cmd, eval, resultRecorder)
        val el = cmd.html()
        renderTable(el, expectedTable)
        setUpDB(expectedTable, parseInsertMode(el))
    }

    private fun setUpDB(table: ITable, insertMode: SeedStrategy) {
        dbTester.executors[ds]!!.apply {
            setUpOperation = insertMode.operation
            dataSet = DefaultDataSet(table)
            onSetup()
        }
    }

    private fun parseInsertMode(el: Html) = if (el.attr("mode") == "add") INSERT else CLEAN_INSERT
}