import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import * as zod from 'zod';
import { useMutation } from '@tanstack/react-query';
import { useNavigate, Link } from 'react-router-dom';
import { api } from '../api/axios';
import { useAuthStore } from '../store/authStore';
import { useState } from 'react';

const loginSchema = zod.object({
  email: zod.string().email('Некоректний формат email'),
  password: zod.string().min(6, 'Пароль має бути не менше 6 символів'),
});

type LoginFields = zod.infer<typeof loginSchema>;

export default function Login() {
  const navigate = useNavigate();
  const loginGlobal = useAuthStore((state) => state.login);
  const [serverError, setServerError] = useState<string | null>(null);

  const { register, handleSubmit, formState: { errors } } = useForm<LoginFields>({
    resolver: zodResolver(loginSchema),
  });

  const mutation = useMutation({
    mutationFn: async (data: LoginFields) => {
      const response = await api.post('/auth/login', data);
      return response.data;
    },
    onSuccess: (data) => {
      loginGlobal(data.token, data.user);
      navigate('/profile');
    },
    onError: (error: any) => {
      if (error.response?.status === 401) {
        setServerError('Невірний email або пароль');
      } else {
        setServerError(error.response?.data?.message || 'Помилка входу');
      }
    },
  });

  const onSubmit = (data: LoginFields) => {
    setServerError(null);
    mutation.mutate(data);
  };

  return (
    <div style={{ maxWidth: '400px', margin: '40px auto', padding: '20px', border: '1px solid #ccc', borderRadius: '8px' }}>
      <h2>Вхід</h2>
      <form onSubmit={handleSubmit(onSubmit)} style={{ display: 'flex', flexDirection: 'column', gap: '15px' }}>
        <div>
          <label style={{ display: 'block', marginBottom: '5px' }}>Email</label>
          <input type="text" {...register('email')} style={{ width: '100%', padding: '8px' }} />
          {errors.email && <p style={{ color: 'red', margin: '5px 0 0' }}>{errors.email.message}</p>}
        </div>

        <div>
          <label style={{ display: 'block', marginBottom: '5px' }}>Пароль</label>
          <input type="password" {...register('password')} style={{ width: '100%', padding: '8px' }} />
          {errors.password && <p style={{ color: 'red', margin: '5px 0 0' }}>{errors.password.message}</p>}
        </div>

        {serverError && <div style={{ color: 'red', padding: '10px', backgroundColor: '#ffebee' }}>{serverError}</div>}

        <button type="submit" disabled={mutation.isPending} style={{ padding: '10px', cursor: 'pointer' }}>
          {mutation.isPending ? 'Вхід...' : 'Увійти'}
        </button>
      </form>
      <p style={{ marginTop: '15px', textAlign: 'center' }}>
        Немає акаунту? <Link to="/register">Зареєструватися</Link>
      </p>
    </div>
  );
}