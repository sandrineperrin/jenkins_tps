FROM python:3.4-alpine
# Create user without password (-D) and home directory (-H)
RUN adduser -D -H ol
ADD . /code
WORKDIR /code
RUN pip install -r requirements.txt
EXPOSE 5000
USER ol
CMD ["python", "app.py"]
