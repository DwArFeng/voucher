# ChangeLog

### Beta_1.0.0_20240321_build_A

#### 功能构建

- 完成 node 模块，打包测试及启动测试通过。

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
