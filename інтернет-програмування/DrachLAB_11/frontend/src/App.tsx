import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:3000';

export default function App() {
  const [selectedFile, setSelectedFile] = useState<File | null>(null);
  const [previewUrl, setPreviewUrl] = useState<string | null>(null);
  const [progress, setProgress] = useState<number>(0);
  const [isUploading, setIsUploading] = useState<boolean>(false);
  const [message, setMessage] = useState<{ text: string; isError: boolean } | null>(null);
  const [uploadedImageUrl, setUploadedImageUrl] = useState<string | null>(null);
  
  const fileInputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if (!selectedFile) {
      setPreviewUrl(null);
      return;
    }

    const url = URL.createObjectURL(selectedFile);
    setPreviewUrl(url);

    return () => {
      URL.revokeObjectURL(url);
    };
  }, [selectedFile]);

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setMessage(null);
    setUploadedImageUrl(null);
    setProgress(0);
    
    if (e.target.files && e.target.files[0]) {
      const file = e.target.files[0];
      setSelectedFile(file);
    }
  };

  const handleUpload = async () => {
    if (!selectedFile) return;

    const allowedTypes = ['image/jpeg', 'image/png', 'image/webp'];
    if (!allowedTypes.includes(selectedFile.type)) {
      setMessage({ text: 'Дозволені тільки формати JPEG, PNG та WEBP!', isError: true });
      return;
    }

    const maxSize = 5 * 1024 * 1024;
    if (selectedFile.size > maxSize) {
      setMessage({ text: 'Розмір файлу не повинен перевищувати 5 МБ!', isError: true });
      return;
    }

    const formData = new FormData();
    formData.append('file', selectedFile);

    setIsUploading(true);
    setMessage(null);

    try {
      const response = await axios.post(`${API_URL}/files`, formData, {
        onUploadProgress: (progressEvent) => {
          if (progressEvent.total) {
            const percentCompleted = Math.round((progressEvent.loaded * 100) / progressEvent.total);
            setProgress(percentCompleted);
          }
        }
      });

      setMessage({ text: 'Файл успішно завантажено!', isError: false });
      setUploadedImageUrl(response.data.url);
      setSelectedFile(null);
      if (fileInputRef.current) fileInputRef.current.value = '';
    } catch (error: any) {
      const serverMessage = error.response?.data?.message || 'Помилка при завантаженні на сервер.';
      setMessage({ text: `Сервер відхилив запит: ${serverMessage}`, isError: true });
    } finally {
      setIsUploading(false);
    }
  };

  return (
    <div style={{ maxWidth: '500px', margin: '40px auto', padding: '20px', fontFamily: 'sans-serif', border: '1px solid #ccc', borderRadius: '8px' }}>
      <h2>ЛР №11: Завантаження зображень</h2>
      
      <input 
        type="file" 
        accept="image/*" 
        onChange={handleFileChange} 
        disabled={isUploading}
        ref={fileInputRef}
      />

      {selectedFile && previewUrl && (
        <div style={{ marginTop: '20px', padding: '10px', border: '1px dashed #aaa' }}>
          <h4>Попередній перегляд:</h4>
          <p style={{ fontSize: '14px' }}><b>Назва:</b> {selectedFile.name}</p>
          <p style={{ fontSize: '14px' }}><b>Розмір:</b> {(selectedFile.size / 1024 / 1024).toFixed(2)} MB</p>
          <img src={previewUrl} alt="Preview" style={{ maxWidth: '100%', maxHeight: '200px', objectFit: 'contain' }} />
          
          <button 
            onClick={handleUpload} 
            disabled={isUploading} 
            style={{ display: 'block', marginTop: '15px', width: '100%', padding: '10px', cursor: 'pointer' }}
          >
            {isUploading ? 'Завантаження...' : 'Відправити на сервер'}
          </button>
        </div>
      )}

      {isUploading && (
        <div style={{ marginTop: '20px' }}>
          <div style={{ width: '100%', backgroundColor: '#eee', borderRadius: '4px', height: '20px' }}>
            <div style={{ width: `${progress}%`, backgroundColor: '#4caf50', height: '100%', borderRadius: '4px', transition: 'width 0.1s', textAlign: 'center', color: 'white', fontSize: '12px', lineHeight: '20px' }}>
              {progress}%
            </div>
          </div>
        </div>
      )}

      {message && (
        <div style={{ marginTop: '20px', padding: '10px', borderRadius: '4px', backgroundColor: message.isError ? '#ffebee' : '#e8f5e9', color: message.isError ? '#c62828' : '#2e7d32' }}>
          {message.text}
        </div>
      )}

      {uploadedImageUrl && (
        <div style={{ marginTop: '20px', padding: '10px', border: '1px solid #4caf50', borderRadius: '4px' }}>
          <h4>Файл на сервері (за URL з відповіді):</h4>
          <a href={uploadedImageUrl} target="_blank" rel="noreferrer" style={{ fontSize: '12px', display: 'block', marginBottom: '10px' }}>{uploadedImageUrl}</a>
          <img src={uploadedImageUrl} alt="Uploaded" style={{ maxWidth: '100%', maxHeight: '200px', objectFit: 'contain' }} />
        </div>
      )}
    </div>
  );
}