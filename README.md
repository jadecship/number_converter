# 数字转换插件说明文档

## 一、简介
本数字转换插件是一个基于 Java 实现的工具，它采用领域设计理念，支持多种数字类型之间的相互转换，如阿拉伯数字、中文小写数字、中文大写数字、罗马数字和天干等。插件能够自动判断用户传入的数字类型，无需用户额外指定，具有良好的可扩展性和易用性。

## 二、功能特性
1. **多种数字类型支持**：支持阿拉伯数字、中文小写数字、中文大写数字、罗马数字和天干的相互转换。
2. **自动类型检测**：能够自动识别用户输入的数字类型，无需手动指定。
3. **可扩展性**：采用领域设计和工厂模式，方便后续添加新的数字类型和转换规则。

## 三、项目结构
```plaintext
number-conversion-plugin/
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── example
│   │               └── numberconversion
│   │                   ├── converter
│   │                   │   ├── AbstractNumberConverter.java
│   │                   │   ├── ArabicToChineseConverter.java
│   │                   │   ├── ChineseToArabicConverter.java
│   │                   │   ├── RomanToArabicConverter.java
│   │                   │   ├── RomanToLowerChineseConverter.java
│   │                   │   ├── RomanToUpperChineseConverter.java
│   │                   │   ├── HeavenlyStemToChineseConverter.java
│   │                   │   └── NumberConverterFactory.java
│   │                   ├── detector
│   │                   │   └── NumberTypeDetector.java
│   │                   ├── model
│   │                   │   ├── NumberType.java
│   │                   │   └── NumberWrapper.java
│   │                   └── Main.java
└── README.md
```

## 四、使用方法

### 1. 克隆项目
在终端或命令提示符中执行以下命令，将项目克隆到本地：
```bash
git clone <你的 GitHub 项目仓库地址>
```

### 2. 导入项目
#### 使用 IntelliJ IDEA
- 打开 IntelliJ IDEA，选择 `File` -> `New` -> `Project from Existing Sources`。
- 在弹出的对话框中，选择刚刚克隆到本地的项目目录，然后点击 `OK`。
- 根据项目的构建工具（如 Maven 或 Gradle），IDEA 会自动识别并导入依赖。

#### 使用 Eclipse
- 打开 Eclipse，选择 `File` -> `Import`。
- 在导入向导中，选择 `Existing Projects into Workspace`，然后点击 `Next`。
- 点击 `Browse` 按钮，选择克隆到本地的项目目录，最后点击 `Finish`。

### 3. 引入依赖（如果使用 Maven 或 Gradle）
#### Maven
在 `pom.xml` 文件中添加以下依赖：
```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>number-conversion-plugin</artifactId>
    <version>你的版本号</version>
</dependency>
```

#### Gradle
在 `build.gradle` 文件中添加：
```groovy
implementation 'com.example:number-conversion-plugin:你的版本号'
```

### 4. 代码示例
以下是一个简单的 Java 代码示例，展示了如何使用该插件进行数字转换：
```java
import com.example.numberconversion.converter.NumberConverterFactory;
import com.example.numberconversion.detector.NumberTypeDetector;
import com.example.numberconversion.model.NumberType;
import com.example.numberconversion.model.NumberWrapper;

public class PluginUsageExample {
    public static void main(String[] args) {
        // 假设要转换的输入值
        Object input = "CXXIII";

        // 自动检测输入值的类型
        NumberWrapper detected = NumberTypeDetector.detect(input);

        // 指定目标转换类型，这里以转换为小写中文数字为例
        NumberType targetType = NumberType.LOWER_CHINESE;

        // 进行转换
        NumberWrapper result = NumberConverterFactory.convert(detected, targetType);

        // 输出转换结果
        System.out.println(input + " 转换为 " + targetType + " 结果是: " + result.getValue());
    }
}
```

### 5. 异常处理
在实际使用中，可能会遇到不支持的转换类型，此时 `NumberConverterFactory.convert` 方法会抛出 `IllegalArgumentException` 异常。建议在代码中添加异常处理逻辑，例如：
```java
import com.example.numberconversion.converter.NumberConverterFactory;
import com.example.numberconversion.detector.NumberTypeDetector;
import com.example.numberconversion.model.NumberType;
import com.example.numberconversion.model.NumberWrapper;

public class PluginUsageExample {
    public static void main(String[] args) {
        Object input = "不支持的输入格式";
        try {
            NumberWrapper detected = NumberTypeDetector.detect(input);
            NumberType targetType = NumberType.LOWER_CHINESE;
            NumberWrapper result = NumberConverterFactory.convert(detected, targetType);
            System.out.println(input + " 转换为 " + targetType + " 结果是: " + result.getValue());
        } catch (IllegalArgumentException e) {
            System.err.println("转换出错: " + e.getMessage());
        }
    }
}
```

## 五、支持的转换类型
| 源类型 | 目标类型 | 说明 |
| ---- | ---- | ---- |
| 阿拉伯数字 | 中文小写数字 | 将阿拉伯数字转换为中文小写数字表示 |
| 阿拉伯数字 | 中文大写数字 | 将阿拉伯数字转换为中文大写数字表示 |
| 中文小写数字 | 阿拉伯数字 | 将中文小写数字转换为阿拉伯数字 |
| 中文大写数字 | 阿拉伯数字 | 将中文大写数字转换为阿拉伯数字 |
| 罗马数字 | 阿拉伯数字 | 将罗马数字转换为阿拉伯数字 |
| 罗马数字 | 中文小写数字 | 将罗马数字转换为中文小写数字 |
| 罗马数字 | 中文大写数字 | 将罗马数字转换为中文大写数字 |
| 天干 | 中文小写数字 | 将天干转换为中文小写数字 |

## 六、扩展功能
如果需要添加新的数字类型或转换规则，可以按照以下步骤进行：
1. 在 `model/NumberType.java` 中添加新的数字类型枚举。
2. 创建一个新的转换器类，继承自 `AbstractNumberConverter`，并实现 `convertInternal` 和 `canConvert` 方法。
3. 在 `NumberConverterFactory` 的静态代码块中添加新的转换器实例。

## 七、贡献与反馈
如果你发现了插件中的 bug 或者有改进建议，欢迎在 GitHub 项目仓库中提交 issue 或 pull request。

## 八、许可
本插件采用 [MIT 许可](https://opensource.org/licenses/MIT)。你可以自由地使用、修改和分发本插件，但请保留原作者信息和许可声明。 
