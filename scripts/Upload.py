import requests
import os
import sys
import json
import hashlib
from requests_toolbelt.multipart.encoder import MultipartEncoder


BASE_URL = 'http://localhost:8080/cloud-doc'
REQUEST_UPLOAD_API = '/drives/nlJjM1J/files/0/request_upload'
UPLOAD_FILE_TO_OBJECT = '/to-object'
COMMIT_UPLOAD_API = '/drives/nlJjM1J/files/0/commit_upload'
TOKEN = 'bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjNiNTkyYWYwLTk5ODktNDRhOC1hMzQ3LTE4Yzc1MDQ4MTlmNCIsInR5cCI6IkpXVCJ9.eyJhaWQiOjE2NTc3MTk2NjUsImF0cCI6InVzZXIiLCJhdHMiOiJ4cFBlREV2IiwiYnVpIjpmYWxzZSwiY2lkIjo2MDAwMDc2NTUsImNsaSI6IkFLMjAyNTA5MThZWllWSEIiLCJleHAiOjE3NjgzNjUxNDMsImpzdCI6ZmFsc2UsInNwaSI6MTc0NTI3MTE5Nn0.7R3vQ-FcZAr8MFF3yquCxunXE7hNqn4wKXY4v9EDG-WwCpHqnJ8eNLepqm6BYp6vM9NWJoAqXKObckrsV8fvaQ'



def calculate_file_hash(file_path):
    hash_func = hashlib.new('sha256')
    
    with open(file_path, 'rb') as f:
        while chunk := f.read(4096):
            hash_func.update(chunk)
    
    return hash_func.hexdigest()

def request_upload(url: str, file_path: str):
    file_name = os.path.basename(file_path)
    file_size = os.path.getsize(file_path)
    hash = calculate_file_hash(file_path)

    header = {'Authorization': TOKEN, 'Content-Type': 'application/json'}
    data = json.dumps({'hashes': [{'sum': hash, 'type': 'sha256'}], 'size': file_size, 'name': file_name})

    try:
        response = requests.post(url=url, data=data, headers=header)
        return response.json()
    except Exception as e:
        sys.stderr.write(f'Upload Failed: {e}')
        sys.exit(1)

def upload_file_to_object(url: str, file_path: str, method: str, obj_url: str):
    try:
        with open(file_path, 'rb') as f:
            multipart_data = MultipartEncoder(
                fields={
                    'file': (os.path.basename(file_path), f, 'application/octet-stream'),
                    'method': 'PUT',
                    'url': obj_url
                }
            )

            headers = {
                'Authorization': TOKEN,
                'Content-Type': multipart_data.content_type
            }

            response = requests.post(url, data=multipart_data, headers=headers)
            
            return response.status_code
    except Exception as e:
        sys.stderr.write(f'Upload Failed: {e}')
        sys.exit(1)

def commit_upload(url: str, upload_id: str):
    try:
        header = {'Authorization': TOKEN, 'Content-Type': 'application/json'}
        data = json.dumps({'upload_id': upload_id})

        response = requests.post(url, data=data, headers=header)
        return response.json()
    except Exception as e:
        sys.stderr.write(f'Upload Failed: {e}')
        sys.exit(1)


if __name__ == '__main__':
    LOCAL_FILE = './test.xlsx'
    request_upload_res = request_upload(BASE_URL + REQUEST_UPLOAD_API, LOCAL_FILE)
    print(f'request_upload_res: {request_upload_res}')
    method = request_upload_res.get('data', {}).get('store_request', {}).get('method')
    obj_url = request_upload_res.get('data', {}).get('store_request', {}).get('url')
    upload_id = request_upload_res.get('data', {}).get('upload_id')
    upload_file_to_object_res = upload_file_to_object(BASE_URL + UPLOAD_FILE_TO_OBJECT, LOCAL_FILE, method, obj_url)
    print(f'upload_file_to_object_res: {upload_file_to_object_res}')
    commit_upload_res = commit_upload(BASE_URL + COMMIT_UPLOAD_API, upload_id)
    print(f'commit_upload_res: {commit_upload_res}')