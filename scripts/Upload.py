import requests
import os
import json
import time
import hashlib
from requests_toolbelt.multipart.encoder import MultipartEncoder

def calculate_file_hash(file_path, hash_algorithm='sha256'):
    """
    计算文件的哈希值
    
    参数:
        file_path: 文件路径
        hash_算法: 哈希算法，如 'md5', 'sha1', 'sha256' (默认为'sha256')
    
    返回:
        哈希值的十六进制字符串
    """
    # 创建哈希对象
    hash_func = hashlib.new(hash_algorithm)
    
    # 以二进制模式读取文件
    with open(file_path, 'rb') as f:
        # 分块读取文件，避免内存不足
        while chunk := f.read(4096):  # 每次读取4KB
            hash_func.update(chunk)  # 更新哈希值
    
    return hash_func.hexdigest()

def upload_file_multipart(url, file_path):
    """
    使用multipart/form-data方式上传文件
    """
    try:
        with open(file_path, 'rb') as f:
            multipart_data = MultipartEncoder(
                fields={
                    'file': (os.path.basename(file_path), f, 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'),
                    'method': 'PUT',
                    'url': 'https://hwc-bj.ag.wps.cn/api/object/1_8257b913a5fe4c21befaab3e544aed39'
                }
            )
            
            # headers = {
            #     'Content-Type': multipart_data.content_type,
            #     'X-File-Name': os.path.basename(file_path),
            #     'X-Timestamp': str(int(time.time()))
            # }

            headers = {
                'Authorization': 'bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjNiNTkyYWYwLTk5ODktNDRhOC1hMzQ3LTE4Yzc1MDQ4MTlmNCIsInR5cCI6IkpXVCJ9.eyJhaWQiOjE2NTc3MTk2NjUsImF0cCI6InVzZXIiLCJhdHMiOiJ4cFBlREV2IiwiYnVpIjpmYWxzZSwiY2lkIjo2MDAwMDc2NTUsImNsaSI6IkFLMjAyNTA5MThZWllWSEIiLCJleHAiOjE3NjgzMzg5OTYsImpzdCI6ZmFsc2UsInNwaSI6MTc0NTI3MTE5Nn0.W0Knk3dqzKe_KPKauLJmqF5z1Au6HK03yDgSzjaFPiDby-CeVAZwQpTS0fknNT7c84bR6QlHEiXud2bh7X0Z9A',
                'Content-Type': multipart_data.content_type
            }
            
            # 发送请求
            response = requests.post(url, data=multipart_data, headers=headers)
            # response = requests.put(url, data=multipart_data, headers=headers)
            return response
            
    except Exception as e:
        print(f"上传失败: {e}")
        return None

if __name__ == '__main__':
    file_path = './e.xlsx'
    # print(calculate_file_hash(file_path))
    # print(os.path.getsize(file_path))

    base_url = 'http://localhost:8080/cloud-doc/to-object'
    # base_url = 'https://ksc-bj.ag.wps.cn/api/object/1_ab8bef7519534a1f9b18fadb6cf9db9c'
    
    endpoints = [f"{base_url}"]
    
    for endpoint in endpoints:
        print(f"\n{'='*60}")
        print(f"测试端点: {endpoint}")
        print('='*60)
        
        try:
            response = upload_file_multipart(endpoint, file_path)
            
            if response is None:
                print("请求创建失败")
                continue
                
            print(f"状态码: {response.status_code}")
            print(f"响应头: {dict(response.headers)}")
            
            if response.status_code == 200:
                try:
                    data = response.json()
                    print(f"响应JSON: {json.dumps(data, ensure_ascii=False, indent=2)}")
                except Exception as json_error:
                    print(f"JSON解析失败: {json_error}")
                    print(f"响应文本: {response.text[:500]}...")
            else:
                print(f"错误响应: {response.text}")
                
        except Exception as e:
            print(f"请求失败: {e}")