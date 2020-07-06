package framework.calcite;

import org.apache.calcite.adapter.java.ReflectiveSchema;
import org.apache.calcite.plan.RelOptUtil;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.RelRoot;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;
import org.apache.calcite.tools.Planner;

/**
 * @author roboslyq
 * @Desc
 * @create 2020-07-07 0:18
 * @since 1.0
 */
public class TestOne {
    public static class TestSchema {
        public final Triple[] rdf = {new Triple("s", "p", "o")};
    }

    public static void main(String[] args) {
        SchemaPlus schemaPlus = Frameworks.createRootSchema(true);

        //给schema T中添加表
        schemaPlus.add("T", new ReflectiveSchema(new TestSchema()));
        Frameworks.ConfigBuilder configBuilder = Frameworks.newConfigBuilder();
        //设置默认schema
        configBuilder.defaultSchema(schemaPlus);

        FrameworkConfig frameworkConfig = configBuilder.build();

        SqlParser.ConfigBuilder paresrConfig = SqlParser.configBuilder(frameworkConfig.getParserConfig());

        //SQL 大小写不敏感
        paresrConfig.setCaseSensitive(false).setConfig(paresrConfig.build());

        Planner planner = Frameworks.getPlanner(frameworkConfig);

        SqlNode sqlNode;
        RelRoot relRoot = null;
        try {
            //parser阶段
            sqlNode = planner.parse("select \"a\".\"s\", count(\"a\".\"s\") from \"T\".\"rdf\" \"a\" group by \"a\".\"s\"");
            //validate阶段
            planner.validate(sqlNode);
            //获取RelNode树的根
            relRoot = planner.rel(sqlNode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RelNode relNode = relRoot.project();
        System.out.print(RelOptUtil.toString(relNode));
    }
}
