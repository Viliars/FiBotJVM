[![Build Status](https://travis-ci.com/Viliars/FiBotJVM.svg?branch=master)](https://travis-ci.org/Viliars/FiBotJVM)
[![Code Coverage](https://codecov.io/github/Viliars/FiBotJVM/coverage.svg)](https://codecov.io/gh/Viliars/FiBotJVM)

# FiBotJVM

Реализация бота FiBot на Java/Kotlin

# Добавление своей команды
1) Создать файл с классом команды, реализующим абстрактный класс viliars.fibot.commands.Command

2) Добавить в CommandManager static init ваш класс по примеру команды Repl

3) (Желательно) Тестирование вашего класса

4) Merge request на master