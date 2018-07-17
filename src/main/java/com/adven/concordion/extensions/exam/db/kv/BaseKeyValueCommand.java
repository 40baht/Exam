package com.adven.concordion.extensions.exam.db.kv;

import com.adven.concordion.extensions.exam.commands.ExamCommand;
import com.adven.concordion.extensions.exam.html.Html;
import com.adven.concordion.extensions.exam.html.HtmlBuilder;
import com.adven.concordion.extensions.exam.rest.JsonPrettyPrinter;

import static com.adven.concordion.extensions.exam.html.HtmlBuilder.*;

public abstract class BaseKeyValueCommand extends ExamCommand {

    protected static final String PROTOBUF = "protobuf";
    protected static final String CACHE = "cache";
    protected static final String KEY = "key";
    protected static final String VALUE = "value";

    public BaseKeyValueCommand(final String name, final String tag) {
        super(name, tag);
    }

    protected Html info(final String text) {
        return div().childs(h(4, text));
    }

    protected Html dbTable(final String cacheName) {
        final Html table = tableSlim();
        final Html header = thead();
        final Html tr = HtmlBuilder.tr();
        tr.childs(
                th("Key"),
                th("Value")
        );
        return table.childs(
                dbCaption(cacheName),
                header.childs(tr));
    }

    protected Html dbCaption(final String cacheName) {
        return caption(cacheName).childs(
                italic("").css("fa fa-database fa-pull-left fa-border")
        );
    }

    protected Html keyColumn(final String key) {
        return td().childs(code(key));
    }

    protected Html valueColumn(final String value) {
        if (isJson(value)) {
            final JsonPrettyPrinter printer = new JsonPrettyPrinter();
            return td(printer.prettyPrint(value)).css("json");
        } else {
            return td().childs(code(value));
        }
    }

    private boolean isJson(final String string) {
        return string.contains("{");
    }

}
