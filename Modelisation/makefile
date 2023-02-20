.SUFFIXES : # Delete the default suffixes

BASE	= modelisation.tex
SOURCES = $(wildcard *.tex)

all:	pdf

pdf:	$(SOURCES)
	pdflatex -output-format pdf $(BASE)
	pdflatex -output-format pdf $(BASE)

md:
	plantuml ./UC/UseCase.md -o ./Diagrammes/UC
	cd UC/UCs/; plantuml "*.md" -o ../Diagrammes/Seq

	cd UC; pandoc -s -f gfm -o ./UseCase.pdf ./UseCase.md --filter hide.py --template=../template.tex

clean:
	rm -f *.bak *~
	rm -f *.log *.aux *.bbl *.blg *~ *.bak *.dvi *.out \
	*.toc *.lof *.lot *.brf
