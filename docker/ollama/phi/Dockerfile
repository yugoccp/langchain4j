FROM ollama/ollama:latest
RUN /bin/ollama serve & sleep 1 && ollama pull phi
ENTRYPOINT ["/bin/ollama"]
CMD ["serve"]