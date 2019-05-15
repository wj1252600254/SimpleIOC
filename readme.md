<h1>功能</h1>
@Inject   将容器中的实例注入到成员变量中，目前只支持成员变量
</br>
json文件配置，必须提供id和class属性，id为bean名字，class为类名，目前只支持String，int两种类型数据解析。
</br>
json支持package属性，可以扫描包下的类。目前还未实现功能
</br>
有@Component,@Dao,@Service三种注解，还未写解析。