.PHONY: css
css:
	mkdir -p assets/css
	sass --watch assets/sass/style.scss:assets/css/app.css --style expanded

.PHONY: mincss
mincss:
	mkdir -p assets/css
	sass assets/sass/style.scss:assets/css/style.css --style compressed
	postcss --use autoprefixer --use postcss-import assets/css/style.css --output assets/css/style.css

.PHONY: server
server:
	browser-sync start --server --files='*.html,assets/css/app.css'

.PHONY: all
all:
	(make css & make server & wait)

.PHONY: clean
clean:
	rm -r assets/css
