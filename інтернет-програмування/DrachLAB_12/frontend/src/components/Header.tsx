import { Link, useNavigate } from 'react-router-dom';
import { useAuthStore } from '../store/authStore';

export default function Header() {
  const { user, logout } = useAuthStore();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <header style={{ display: 'flex', justifyContent: 'space-between', padding: '15px', borderBottom: '1px solid #ccc' }}>
      <Link to="/" style={{ fontWeight: 'bold', textDecoration: 'none' }}>Головна</Link>
      <nav>
        {user ? (
          <div style={{ display: 'flex', gap: '15px', alignItems: 'center' }}>
            <span>{user.email}</span>
            <Link to="/profile">Профіль</Link>
            <button onClick={handleLogout}>Вийти</button>
          </div>
        ) : (
          <div style={{ display: 'flex', gap: '15px' }}>
            <Link to="/login">Увійти</Link>
            <Link to="/register">Реєстрація</Link>
          </div>
        )}
      </nav>
    </header>
  );
}