# ChangeLog

### Beta_1.0.1_20240406_build_A

#### 功能构建

- 日志功能优化。
  - 优化默认日志配置，默认配置仅向控制台输出 `INFO` 级别的日志。
  - 优化日志配置结构，提供 `conf/logging/settings.xml` 配置文件及其不同平台的参考配置文件，以供用户自定义日志配置。
  - 优化日志配置结构，提供 `confext/logging-settings.xml` 配置文件，以供外部功能自定义日志配置。
  - 优化启动脚本，使服务支持新的日志配置结构。
  - 优化 `assembly.xml`，使项目打包时输出新的日志配置结构。
  - 优化 `confext/README.md`，添加新的日志配置结构的相关说明。

- 添加 `LICENSE`。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Beta_1.0.0_20240405_build_A

#### 功能构建

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

#### Bug修复

- (无)

#### 功能移除

- (无)
