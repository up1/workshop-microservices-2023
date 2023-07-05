FROM python:3.12.0b3-alpine3.18
WORKDIR	/app
COPY requirements.txt requirements.txt
RUN pip install --no-cache-dir -r requirements.txt
COPY . .
EXPOSE 5000
CMD ["python", "server.py"]