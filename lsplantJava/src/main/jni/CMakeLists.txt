cmake_minimum_required(VERSION 3.18.1)
project("lsplant_hook")

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

add_library(lsplantHook SHARED lsplant_hook.cpp elf_util.cpp)
find_package(dobby REQUIRED CONFIG)
find_package(lsplant REQUIRED CONFIG)
target_link_libraries(lsplantHook log dobby::dobby lsplant::lsplant)
