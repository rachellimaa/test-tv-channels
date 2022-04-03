#!/bin/bash

echo
echo -----------------------------------------------------------------------------------------------
echo clean_project
echo -----------------------------------------------------------------------------------------------
echo
echo "Cleaning build folders..."

# Cleanup folders
rm -rf **/.cxx/
rm -rf **/.gradle/
rm -rf **/build/
