import { Navigate, Outlet } from 'react-router-dom';
import { useAuthStore } from '../store/authStore';

interface ProtectedRouteProps {
  allowedAuthenticated: boolean;
}

export default function ProtectedRoute({ allowedAuthenticated }: ProtectedRouteProps) {
  const { user, isLoading } = useAuthStore();

  if (isLoading) {
    return <div>Завантаження...</div>;
  }

  if (allowedAuthenticated && !user) {
    return <Navigate to="/login" replace />;
  }

  if (!allowedAuthenticated && user) {
    return <Navigate to="/profile" replace />;
  }

  return <Outlet />;
}