# ChangeLog

## Release_1.1.0_20251206_build_A

### 功能构建

- 优化部分代码的文档注释。
  - com.dwarfeng.voucher.stack.handler.VoucherHandler。
  - com.dwarfeng.voucher.stack.service.VoucherQosService。
  - com.dwarfeng.voucher.stack.service.VoucherService。

- 优化 impl 模块下的 `logging` 目录结构。
  - 将 `logging/settings-windows.xml` 重命名为 `settings-ref-windows.xml`，以消除文件名的歧义。
  - 更新 `logging/README.md` 中的相关说明。

- 优化 node 模块下的 `logging` 目录结构。
  - 将 `logging/settings-linux.xml` 重命名为 `settings-ref-linux.xml`，以消除文件名的歧义。
  - 将 `logging/settings-windows.xml` 重命名为 `settings-ref-windows.xml`，以消除文件名的歧义。
  - 更新 `logging/README.md` 中的相关说明。

- 优化部分维护服务实现中的部分方法的性能。
  - com.dwarfeng.voucher.impl.service.CheckerInfoMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.CheckerSupportMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.VoucherCategoryMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.VoucherCategoryVariableMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.VoucherMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.VoucherVariableMaintainServiceImpl。

- 优化部分类中部分方法的行为分析行为。
  - com.dwarfeng.voucher.impl.service.CheckerInfoMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.VoucherCategoryMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.VoucherCategoryVariableMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.VoucherMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.VoucherVariableMaintainServiceImpl。

- SPI 目录结构优化。
  - 将检查机制的 SPI 接口与抽象类提相关代码文件提升至 `sdk` 模块中。
  - 将推送机制的 SPI 接口与抽象类提相关代码文件提升至 `sdk` 模块中。
  - 将重置机制的 SPI 抽象类提相关代码文件提升至 `sdk` 模块中。

- 导入运维指令。
  - com.dwarfeng.datamark.service.telqos.*。

- 增加 Hibernate 实体数据标记字段，并应用相关实体侦听器。
  - com.dwarfeng.voucher.impl.bean.entity.HibernateCheckerInfo。
  - com.dwarfeng.voucher.impl.bean.entity.HibernateVoucherCategory。

- 增加依赖。
  - 增加依赖 `dwarfeng-datamark` 以应用其新功能，版本为 `1.0.4.a`。

- 优化部分类构造器方法中的参数名。
  - com.dwarfeng.voucher.impl.handler.pusher.AbstractPusher。

- 优化支持实体机制。
  - 新建支持 QoS 服务 com.dwarfeng.voucher.stack.service.SupportQosService。
  - 将支持实体维护服务的重置功能迁移至 QoS 服务。

- 依赖升级。
  - 升级 `subgrade` 依赖版本为 `1.6.0.a` 并解决兼容性问题，以规避漏洞。
  - 升级 `spring` 依赖版本为 `5.3.39` 以规避漏洞。
  - 升级 `kafka` 依赖版本为 `3.9.0` 以规避漏洞。
  - 升级 `protobuf` 依赖版本为 `3.25.5` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.119.Final` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.9.4` 以规避漏洞。
  - 升级 `slf4j` 依赖版本为 `1.7.36` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.7.2.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.14.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.15.a` 以规避漏洞。
  - 升级 `jackson` 依赖版本为 `2.18.3` 以规避漏洞。
  - 升级 `groovy` 依赖版本为 `4.0.26` 以规避漏洞。

- 优化开发环境支持。
  - 在 .gitignore 中添加 VSCode 相关文件的忽略规则。
  - 在 .gitignore 中添加 Cursor IDE 相关文件的忽略规则。

### Bug 修复

- 修复部分功能启动延时为 0 时行为不正确的 bug。
  - 清理服务启动。

### 功能移除

- 去除支持实体维护服务的重置功能。
  - com.dwarfeng.voucher.stack.service.CheckerSupportMaintainService。

---

## Beta_1.0.1_20240406_build_A

### 功能构建

- 日志功能优化。
  - 优化默认日志配置，默认配置仅向控制台输出 `INFO` 级别的日志。
  - 优化日志配置结构，提供 `conf/logging/settings.xml` 配置文件及其不同平台的参考配置文件，以供用户自定义日志配置。
  - 优化日志配置结构，提供 `confext/logging-settings.xml` 配置文件，以供外部功能自定义日志配置。
  - 优化启动脚本，使服务支持新的日志配置结构。
  - 优化 `assembly.xml`，使项目打包时输出新的日志配置结构。
  - 优化 `confext/README.md`，添加新的日志配置结构的相关说明。

- 添加 `LICENSE`。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Beta_1.0.0_20240405_build_A

### 功能构建

- 实现运维指令。
  - com.dwarfeng.voucher.impl.service.telqos.CheckLocalCacheCommand。
  - com.dwarfeng.voucher.impl.service.telqos.VoucherCommand。
  - com.dwarfeng.voucher.impl.service.telqos.ResetCommand。
  - com.dwarfeng.voucher.impl.service.telqos.CleanupCommand。

- 完成 node 模块，打包测试及启动测试通过。

- 实现预设推送器。
  - com.dwarfeng.voucher.impl.handler.pusher.DrainPusher。
  - com.dwarfeng.voucher.impl.handler.pusher.LogPusher。
  - com.dwarfeng.voucher.impl.handler.pusher.MultiPusher。
  - com.dwarfeng.voucher.impl.handler.pusher.NativeKafkaPusher。

- 实现预设重置器。
  - com.dwarfeng.voucher.impl.handler.resetter.CronResetter。
  - com.dwarfeng.voucher.impl.handler.resetter.DubboResetter。
  - com.dwarfeng.voucher.impl.handler.resetter.FixedDelayResetter。
  - com.dwarfeng.voucher.impl.handler.resetter.FixedRateResetter。
  - com.dwarfeng.voucher.impl.handler.resetter.NeverResetter。

- 实现预设检查器。
  - com.dwarfeng.voucher.impl.handler.checker.GroovyCheckerRegistry。
  - com.dwarfeng.voucher.impl.handler.checker.SingleUseCheckerRegistry。

- 实现凭证查询服务。
  - com.dwarfeng.voucher.stack.service.VoucherService。

- 实现核心机制。
  - 检查机制。
  - 清理机制。
  - 重置机制。
  - 推送机制。

- 实现实体的操作处理器。
  - com.dwarfeng.voucher.stack.handler.VoucherCategoryVariableOperateHandler。
  - com.dwarfeng.voucher.stack.handler.VoucherOperateHandler。
  - com.dwarfeng.voucher.stack.handler.VoucherVariableOperateHandler。

- 实现实体的操作服务。
  - com.dwarfeng.voucher.stack.service.CheckerInfoMaintainService。

- 定义实体及其维护服务，并通过单元测试。
  - com.dwarfeng.voucher.stack.bean.entity.CheckerInfo。
  - com.dwarfeng.voucher.stack.bean.entity.CheckerSupport。
  - com.dwarfeng.voucher.stack.bean.entity.Voucher。
  - com.dwarfeng.voucher.stack.bean.entity.VoucherCategory。
  - com.dwarfeng.voucher.stack.bean.entity.VoucherCategoryVariable。
  - com.dwarfeng.voucher.stack.bean.entity.VoucherVariable。

- 项目结构建立，清理测试通过。

### Bug 修复

- (无)

### 功能移除

- (无)
