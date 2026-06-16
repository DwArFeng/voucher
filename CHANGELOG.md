# ChangeLog

## Release_2.0.0_20260614_build_A

### 功能构建

- 配置命名空间全局唯一化改造。
  - 为 `voucher-impl` 模块配置键统一增加全球唯一前缀，以消除跨微服务同名配置冲突。
  - 为 `voucher-node` 模块配置键统一增加全球唯一前缀，以消除跨微服务同名配置冲突。
  - 处理 `redis/prefix.properties` 缓存前缀配置的实体命名空间。
  - 同步调整 `*.java` 中的配置读取占位符。
  - 同步调整 `*application-context-*.xml` 中的配置读取占位符。

- 部分代理类实现中的字段类型提升为对应的接口，与具体实现解耦。
  - com.dwarfeng.voucher.impl.cache.CheckerInfoCacheImpl。
  - com.dwarfeng.voucher.impl.cache.CheckerSupportCacheImpl。
  - com.dwarfeng.voucher.impl.cache.VoucherCacheImpl。
  - com.dwarfeng.voucher.impl.cache.VoucherCategoryCacheImpl。
  - com.dwarfeng.voucher.impl.cache.VoucherCategoryVariableCacheImpl。
  - com.dwarfeng.voucher.impl.cache.VoucherVariableCacheImpl。
  - com.dwarfeng.voucher.impl.dao.CheckerInfoDaoImpl。
  - com.dwarfeng.voucher.impl.dao.CheckerSupportDaoImpl。
  - com.dwarfeng.voucher.impl.dao.VoucherCategoryDaoImpl。
  - com.dwarfeng.voucher.impl.dao.VoucherCategoryVariableDaoImpl。
  - com.dwarfeng.voucher.impl.dao.VoucherDaoImpl。
  - com.dwarfeng.voucher.impl.dao.VoucherVariableDaoImpl。
  - com.dwarfeng.voucher.impl.service.CheckerInfoMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.CheckerSupportMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.VoucherCategoryMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.VoucherCategoryVariableMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.VoucherMaintainServiceImpl。
  - com.dwarfeng.voucher.impl.service.VoucherVariableMaintainServiceImpl。

- 优化项目的异常处理机制。
  - `voucher-sdk` 子模块新增 `ServiceExceptionHelper` 工具类，统一维护项目自身的异常映射关系。
  - `voucher-impl` 子模块 `ServiceExceptionMapperConfiguration` 配置类的异常映射处理逻辑优化。
  - `voucher-node` 子模块 `ServiceExceptionMapperConfiguration` 配置类的异常映射处理逻辑优化。

- 依赖升级。
  - 升级 `spring-telqos` 依赖版本为 `2.0.2.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `spring-terminator` 依赖版本为 `2.0.2.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `dwarfeng-datamark` 依赖版本为 `2.2.0.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `snowflake` 依赖版本为 `2.0.2.a` 以规避漏洞。

- 优化文件格式。
  - 优化 `*.properties` 文件的格式。
  - 优化 `application-context-*.xml` 文件的格式。
  - 优化 `pom.xml` 文件的格式。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## 更早的版本

[View all changelogs](./changelogs)
