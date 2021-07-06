# tw-git-commit-message-helper
This plugin is an upgraded version of the source idea plugin [git-commit-message-helper](https://plugins.jetbrains.com/plugin/13477-git-commit-message-helper/) , added some specific functions which is used to help ThoughtWorks employees submit git commit messages in a standardized way.


## Install
Install directly from the IDE plugin manager (File > Settings > Plugins > Browser repositories > ThoughtWorks Git Commit Message Helper
)

## Usage

### Normal-Commit message without conversion

#### Setting your name
![setting-your-name](https://imdreamer.oss-cn-hangzhou.aliyuncs.com/git-commit-message-helper/setting-1.png)

#### How to use normal commit

![normal](https://imdreamer.oss-cn-hangzhou.aliyuncs.com/git-commit-message-helper/normal.gif)

### Translation- Translate the commit message from Chinese to English

#### Setting your google-translate-api-key

Please refer to this [link](https://cloud.google.com/translate/docs/setup) to get your google translate api key

#### How to use translation commit

![translation](https://imdreamer.oss-cn-hangzhou.aliyuncs.com/git-commit-message-helper/translation.gif)

### Grammatical-Correct the spelling and grammar of the commit message in English

#### Setting your grammatical-correct-api

Please refer to this [link](https://github.com/imdreamer2018/Grammatical-Error-Correction) , install your grammatical error correction service, You can build your docker images or pull my docker images. However, the service does not support the correction of Chinese English for the time being, and the model will continue to be trained to support correction in the future.

grammatical-correct-api default value is `http://127.0.0.1:21046/api/texts`

#### How to use grammatical commit

![grammatical](https://imdreamer.oss-cn-hangzhou.aliyuncs.com/git-commit-message-helper/grammatical.gif)

### Change the type of commit

![Change-the-type-of-commit](https://imdreamer.oss-cn-hangzhou.aliyuncs.com/git-commit-message-helper/setting-2.png)

### personalize your commit template

![personalize-your-commit-template](https://imdreamer.oss-cn-hangzhou.aliyuncs.com/git-commit-message-helper/setting-3.png)

# License

Licensed under the  [Apache License](http://www.apache.org/licenses/LICENSE-2.0), Version 2.0 (the "License"); you may not use this file except in compliance with the License.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

#### thank you for   https://github.com/AutismSuperman/git-commit-message-helper
#### thank you for   https://github.com/MobileTribe/commit-template-idea-plugin
#### thank you for   https://github.com/MobileTribe/commit-template-idea-plugin
#### thank you for   https://github.com/x-hansong/CodeMaker
#### thank you for   https://github.com/shuzijun/leetcode-editor
