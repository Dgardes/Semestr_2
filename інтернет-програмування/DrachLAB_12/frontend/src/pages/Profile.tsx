import { useAuthStore } from '../store/authStore';

export default function Profile() {
  const { user } = useAuthStore();

  if (!user) return null;

  return (
    <div style={{ maxWidth: '500px', margin: '40px auto', padding: '20px', border: '1px solid #ccc', borderRadius: '8px' }}>
      <h2>Мій профіль</h2>
      <div style={{ lineHeight: '2' }}>
        <p><b>ID користувача:</b> {user.id}</p>
        <p><b>Email:</b> {user.email}</p>
        <p><b>Дата створення акаунта:</b> {new Date(user.createdAt).toLocaleDateString()}</p>
      </div>
    </div>
  );
}