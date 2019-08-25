[![Build Status](https://travis-ci.com/Viliars/FiBotJVM.svg?branch=master)](https://travis-ci.org/Viliars/FiBotJVM)
[![Code Coverage](https://codecov.io/github/Viliars/FiBotJVM/coverage.svg)](https://codecov.io/gh/Viliars/FiBotJVM)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
# FiBotJVM

Реализация бота FiBot на Java/Kotlin

# Добавление своей команды
1) Создать файл с классом команды, реализующим абстрактный класс viliars.fibot.commands.Command

2) Добавить в CommandManager static init ваш класс по примеру команды Repl

3) (Желательно) Тестирование вашего класса

4) Pull request на master
