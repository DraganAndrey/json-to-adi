## Cервис подготовки мета данных к загрузке на ТВ Платформу.

### Настройка окружения
Для корректной работы приложения необходимо наличие следующих установленных компонентов:
* Java version 1.8
* Maven3
### Описание параметров в файле application.properties:
| Параметр | Значение по-умолчанию | Описание |
| ------ | ------ |------ |
| output.path.xml|  | Путь до директории вывода результатов конвертации

### Установка и запуск приложения

Перейти в каталог с проектом:
```sh
$ cd ~/**Путь до каталога с проектом**/json-to-adi
```
Собрать исполняемый jar: 
```sh
$ mvn clean install
```
Выполнить запуск исполняемого файла. В качестве первого аргумента, необходимо указать Json файл для конвертации. 
```sh
$ java -jar target/JsonToADIConverter-0.0.1-SNAPSHOT.jar /**входной json для конвертации**/
```
### Принцип работы
Сервис JsonToADIConverter - предназначен для конвертации данных VOD контента в формате _JSON_ и преобразования их в файлы _.xml_ согласно стандарту _ADI 1.1_.

### Пример входного Json
Пример входного JSON можно посмотерть в папке _test/resources/data_ директории проекта.

