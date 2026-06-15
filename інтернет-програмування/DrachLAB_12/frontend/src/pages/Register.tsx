import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import * as zod from 'zod';
import { useMutation } from '@tanstack/react-query';
import { useNavigate, Link } from 'react-router-dom';
import { api } from '../api/axios';
import { useState } from 'react';

const registerSchema = zod.object({
  email: zod.string().email('Некоректний формат email'),
  password: zod.string().min(6, 'Пароль має бути не менше 6 символів'),
});

type RegisterFields = zod.infer<typeof registerSchema>;

export default function Register() {
  const navigate = useNavigate();
  const [serverError, setServerError] = useState<string | null>(null);

  const { register, handleSubmit, formState: { errors } } = useForm<RegisterFields>({
    resolver: zodResolver(registerSchema),
  });

  const mutation = useMutation({
    mutationFn: async (data: RegisterFields) => {
      return api.post('/auth/register', data);
    },
    onSuccess: () => {
      alert('Акаунт успішно створено!');
      navigate('/login');
    },
    onError: (error: any) => {
      if (error.response?.status === 409) {
        setServerError('Користувач з таким email вже існує');
      } else {
        setServerError(error.response?.data?.message || 'Помилка реєстрації');
      }
    },
  });

  const onSubmit = (data: RegisterFields) => {
    setServerError(null);
    mutation.mutate(data);
  };

  return (
    <div style={{ maxWidth: '400px', margin: '40px auto', padding: '20px', border: '1px solid #ccc', borderRadius: '8px' }}>
      <h2>Реєстрація</h2>
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
          {mutation.isPending ? 'Реєстрація...' : 'Зареєструватися'}
        </button>
      </form>
      <p style={{ marginTop: '15px', textAlign: 'center' }}>
        Вже маєте акаунт? <Link to="/login">Увійти</Link>
      </p>
    </div>
  );
}