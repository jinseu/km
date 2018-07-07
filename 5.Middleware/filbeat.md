## Filebeat

> filebeat 版本5.6

filebeats是知名的ELK日志分析套件的一部分。它的前身是logstash-forwarder，用于收集日志并转发给后端（logstash、elasticsearch、redis、kafka等等）。filebeat是beats项目中的一种beats，负责收集日志文件的新增内容。

libbeat 集合了各个 beat 会用到的内容，包括公共的配置，输出的管理等等。每个beat专注于自己的收集工作，然后转发给libbeat进一步处理和输出。

每个 beat 的构建是独立的。从 filebeat 的入口文件filebeat/main.go可以看到，它向libbeat传递了名字、版本和构造函数来构造自身。跟着走到libbeat/beater/beater.go，我们可以看到程序的启动时的主要工作都是在这里完成的，包括命令行参数的处理、通用配置项的解析，以及最为重要的：调用象征一个beat的生命周期的若干方法。

基本组件以及处理流程如下：

- prospector: finds files in paths/globs to harvest, starts harvesters
- harvester: reads a file, sends events to the spooler
- spooler: buffers events until ready to flush to the publisher, spooler本身是纺纱机，筒子车的意思。
- publisher: writes to the network, notifies registrar
- registrar: records positions of files read
- Finally, prospector uses the registrar information, on restart, to determine where in each file to restart a harvester.

filebeat 启动代码

```

"github.com/elastic/beats/filebeat/beater"
"github.com/elastic/beats/libbeat/beat"

var Name = "filebeat"

func main() {
	if err := beat.Run(Name, "", beater.New); err != nil {
		os.Exit(1)
	}
}
```

然后在libbeat中会调用如下方法创建一个Beat

```
func Run(name, version string, bt Creator) error {
	return handleError(newBeat(name, version).launch(bt))
}

// newBeat creates a new beat instance
func newBeat(name, version string) *Beat {
	if version == "" {
		version = defaultBeatVersion
	}

	return &Beat{
		Name:    name,
		Version: version,
		UUID:    uuid.NewV4(),
	}
}
```
Beat 结构体内容如下：

```
type Beat struct {
	Name      string              // Beat name.
	Version   string              // Beat version number. Defaults to the libbeat version when an implementation does not set a version.
	UUID      uuid.UUID           // ID assigned to a Beat instance.
	RawConfig *common.Config      // Raw config that can be unpacked to get Beat specific config data.
	Config    BeatConfig          // Common Beat configuration data.
	Publisher publisher.Publisher // Publisher

	SetupMLCallback SetupMLCallback // setup callback for ML job configs
	InSetupCmd      bool            // this is set to true when the `setup` command is called
}
```

