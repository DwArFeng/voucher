# Quick Start - 快速开始

## 确认系统需求

- CPU：2 核以上。
- 内存：4G 以上。
- 硬盘：100G 以上。
- CentOS 7。
- JRE 1.8。
- MySQL 8.0.19。
- Redis 5.0.7。
- Zookeeper 3.5.5。
- snowflake-distributed-service 1.8.3.a。

## 获取软件包

从 Github 上获取软件包，软件包可以从 Github 的 Release 页面下载。

## 解压软件包

软件包的名称格式为 `voucher-all-he-${version}-release.tar.gz`，其中 `${version}` 为软件包的版本号。

使用工具软件，将软件包上传至服务器 `/usr/local` 目录下，解压软件包。

```shell
cd /usr/local
tar -zxvf voucher-all-he-${version}-release.tar.gz
mv voucher-all-he-${version} voucher
```

## 数据库初始化

连接到 MySQL 数据库，执行如下 SQL 语句：

```sql
# noinspection SpellCheckingInspectionForFile
-- QuickStart 最小凭证初始化脚本（SingleUseChecker）
-- 适用数据库：MySQL 8+
-- 说明：
-- 1. 本脚本用于在空库或新库中构建最小可跑通凭证链路。
-- 2. 若与现有数据并存，请先评估主键冲突风险。

CREATE DATABASE IF NOT EXISTS `voucher` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin;
USE `voucher`;

-- -----------------------------------------------------
-- 基础表结构（最小子集）
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tbl_checker_info`
(
    `id`                varchar(100) NOT NULL,
    `enabled`           bit(1)       NOT NULL,
    `param`             text,
    `remark`            varchar(200) DEFAULT NULL,
    `type`              varchar(50)  DEFAULT NULL,
    `created_datamark`  varchar(100) DEFAULT NULL,
    `modified_datamark` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_voucher_category`
(
    `id`                varchar(50) NOT NULL,
    `enabled`           bit(1)      NOT NULL,
    `name`              varchar(50) NOT NULL,
    `remark`            varchar(200) DEFAULT NULL,
    `created_datamark`  varchar(100) DEFAULT NULL,
    `modified_datamark` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_voucher`
(
    `id`          bigint NOT NULL,
    `category_id` varchar(100) DEFAULT NULL,
    `content`     text,
    `remark`      varchar(200) DEFAULT NULL,
    `valid`       bit(1) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKhrfjbas8hmm3t8j1hpu644cl3` (`category_id`),
    CONSTRAINT `FKhrfjbas8hmm3t8j1hpu644cl3` FOREIGN KEY (`category_id`) REFERENCES `tbl_voucher_category` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_voucher_category_variable`
(
    `variable_id`         varchar(100) NOT NULL,
    `voucher_category_id` varchar(100) NOT NULL,
    `last_updated_date`   datetime(6) DEFAULT NULL,
    `value`               text,
    PRIMARY KEY (`variable_id`, `voucher_category_id`),
    KEY `FKkrsbxlkkv6htb7j41nkkjev4f` (`voucher_category_id`),
    CONSTRAINT `FKkrsbxlkkv6htb7j41nkkjev4f` FOREIGN KEY (`voucher_category_id`) REFERENCES `tbl_voucher_category` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_voucher_variable`
(
    `variable_id`       varchar(100) NOT NULL,
    `voucher_id`        bigint       NOT NULL,
    `last_updated_date` datetime(6) DEFAULT NULL,
    `value`             text,
    PRIMARY KEY (`variable_id`, `voucher_id`),
    KEY `FKoltqjrwtfcev4sx91qw4bomt7` (`voucher_id`),
    CONSTRAINT `FKoltqjrwtfcev4sx91qw4bomt7` FOREIGN KEY (`voucher_id`) REFERENCES `tbl_voucher` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_bin;

-- -----------------------------------------------------
-- 最小凭证链路数据
-- -----------------------------------------------------
INSERT INTO `tbl_checker_info` (`id`, `enabled`, `param`, `remark`, `type`, `created_datamark`,
                                `modified_datamark`)
VALUES ('foobar', b'1',
        '{"#expire_date_offset":"过期时间偏移量, 单位为毫秒. 生成凭证时, 凭证的过期时间 = 系统时间 + 过期时间偏移量. 偏移量小于等于 0 时, 代表凭证永不过期, 只有查看后才能使凭证失效.","expire_date_offset":3600000}',
        '测试用', 'single_use_checker', NULL, NULL)
ON DUPLICATE KEY UPDATE `enabled` = VALUES(`enabled`),
                        `param`   = VALUES(`param`),
                        `remark`  = VALUES(`remark`),
                        `type`    = VALUES(`type`);

INSERT INTO `tbl_voucher_category` (`id`, `enabled`, `name`, `remark`, `created_datamark`, `modified_datamark`)
VALUES ('foobar', b'1', '测试用', '测试用', NULL, NULL)
ON DUPLICATE KEY UPDATE `enabled` = VALUES(`enabled`),
                        `name`    = VALUES(`name`),
                        `remark`  = VALUES(`remark`);
```

说明：

- 上述 SQL 用于构建 QuickStart 最小凭证链路（SingleUseChecker）。
- 若使用的是全新数据库，程序启动后 Hibernate 仍会根据实体自动补齐其它业务表（`hibernate.hbm2ddl.auto=update`）。

## 最小化配置

下文列出了启动程序需要改动的最少配置文件，每个配置文件中仅展示需要改动的配置项。

`conf/curator/connection.properties` 文件中配置 curator 连接信息。

```properties
com.dwarfeng.voucher.curator.connect.connect_string=your-host-here:2181
```

`conf/database/connection.properties` 文件中配置数据库连接信息。

```properties
com.dwarfeng.voucher.jdbc.url=jdbc:mysql://your-host-here:3306/voucher?serverTimezone=Asia/Shanghai&autoReconnect=true
com.dwarfeng.voucher.jdbc.username=root
com.dwarfeng.voucher.jdbc.password=your-password-here
```

`conf/dubbo/connection.properties` 文件中配置 dubbo 连接信息。

```properties
com.dwarfeng.voucher.dubbo.registry.zookeeper.address=zookeeper://your-host-here:2181
com.dwarfeng.voucher.dubbo.protocol.dubbo.host=your-host-here
```

`conf/redis/connection.properties` 文件中配置 redis 连接信息。

```properties
com.dwarfeng.voucher.redis.hostName=your-host-here
com.dwarfeng.voucher.redis.port=6379
com.dwarfeng.voucher.redis.password=your-password-here
```

`conf/voucher/launcher.properties` 中确认如下启动项，使程序启动后重置检查器支持。

```properties
com.dwarfeng.voucher.launcher.reset_checker_support=true
```

## 修改可选配置

下文列出了启动程序需要改动的可选的配置文件，每个配置文件中仅展示需要改动的配置项。

`opt/opt-checker.xml` 检查器可选配置。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- ↓ 以下注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringXmlModelInspection -->
<!-- ↑ 以上注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 扫描 handler 的实现包。 -->
    <context:component-scan base-package="com.dwarfeng.voucher.impl.handler.checker" use-default-filters="false">
        <!-- 加载 GroovyChecker -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.voucher.impl.handler.checker.GroovyCheckerRegistry"
        />
        -->

        <!-- 加载 SingleUseChecker -->
        <context:include-filter
                type="assignable" expression="com.dwarfeng.voucher.impl.handler.checker.SingleUseCheckerRegistry"
        />
    </context:component-scan>
</beans>

```

`opt/opt-pusher.xml` 推送器可选配置。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- ↓ 以下注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringXmlModelInspection -->
<!-- ↑ 以上注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 扫描 handler 的实现包。 -->
    <context:component-scan
            base-package="com.dwarfeng.voucher.impl.handler.pusher" use-default-filters="false"
    >
        <!-- 加载 DrainPusher -->
        <context:include-filter
                type="assignable" expression="com.dwarfeng.voucher.impl.handler.pusher.DrainPusher"
        />

        <!-- 加载 LogPusher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.voucher.impl.handler.pusher.LogPusher"
        />
        -->

        <!-- 加载 MultiPusher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.voucher.impl.handler.pusher.MultiPusher"
        />
        -->

        <!-- 加载 NativeKafkaPusher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.voucher.impl.handler.pusher.NativeKafkaPusher"
        />
        -->
    </context:component-scan>
</beans>
```

## 启动程序

在 `/usr/local/voucher` 目录下执行如下命令：

```shell
sh bin/voucher-start.sh
```

1. 观察数据库，数据库将会自动生成 `tbl_` 前缀的表，并具有部分数据。
2. 观察 Redis，Redis 将会自动生成 `com.dwarfeng.voucher.entity.` 前缀的缓存键，并具有部分数据。

## 停止程序

在 `/usr/local/voucher` 目录下执行如下命令：

```shell
sh bin/voucher-stop.sh
```
