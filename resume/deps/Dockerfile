FROM fedora:latest

RUN dnf update -y && dnf upgrade -y

RUN dnf install -y fontconfig \
	texlive texlive-latex texlive-xetex texlive-collection-latex \
	texlive-collection-latexrecommended texlive-xetex-def \
	texlive-collection-xetex texlive-fontawesome texlive-xargs

COPY fonts/* /usr/local/share/fonts/

RUN fc-cache -fv

WORKDIR /resume

VOLUME /resume