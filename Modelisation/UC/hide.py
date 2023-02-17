#!/usr/bin/env python3

import os
import sys

from pandocfilters import toJSONFilter, Para, Image

# The filter is called to delete the code block inside a div with hidden attribute


def action(key, value, format, meta):
    if key == 'CodeBlock':
        [[ident, classes, keyvals], code] = value

        if "plantuml" in classes:
            return Para([])


def main():
    toJSONFilter(action)


if __name__ == "__main__":
    main()
