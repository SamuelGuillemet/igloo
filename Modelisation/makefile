.SUFFIXES : # Delete the default suffixes

BASE	= modelisation.tex
SOURCES = $(wildcard *.tex)

all:	pdf

pdf:	$(SOURCES)
	pdflatex -output-format pdf $(BASE)
	pdflatex -output-format pdf $(BASE)

clean:
	rm -f *.bak *~
	rm -f *.log *.aux *.bbl *.blg *~ *.bak *.dvi *.out \
	*.toc *.lof *.lot *.brf
