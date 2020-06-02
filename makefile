CLANG_FORMAT=node_modules/clang-format/bin/linux_x64/clang-format --style=Google
CSS_VALIDATOR=node_modules/css-validator/bin/css-validator
ESLINT=node_modules/eslint/bin/eslint.js
HTML_VALIDATE=node_modules/html-validate/bin/html-validate.js
PRETTIER=node_modules/prettier/bin-prettier.js

node_modules:
	npm install clang-format prettier css-validator html-validate eslint eslint-config-google

pretty: node_modules
	find portfolio/src/main -iname *.html -o -iname *.css | xargs $(PRETTIER) --write
	find portfolio/src/main -iname *.java | xargs $(CLANG_FORMAT) -i
	find portfolio/src/main -iname *.js | xargs $(CLANG_FORMAT) -i

validate: node_modules
	find portfolio/src/main -iname *.html | xargs $(HTML_VALIDATE)
	find portfolio/src/main -iname *.css | xargs $(CSS_VALIDATOR)
	find portfolio/src/main -iname *.js | xargs $(ESLINT)

package:
	mvn package