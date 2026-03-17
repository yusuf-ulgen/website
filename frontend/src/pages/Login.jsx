import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import myLogo from '../assets/logo.png';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');
    setIsLoading(true);
    try {
      const result = await login(username, password);
      if (result.success && result.data) {
        localStorage.setItem('token', result.data);
        navigate('/admin/dashboard');
      } else {
        const errorMessage = result.errors && result.errors.length > 0
          ? result.errors[0]
          : 'Giriş başarısız! Bilgilerinizi kontrol edin.';
        setError(errorMessage);
      }
    } catch (err) {
      console.error('Giriş hatası:', err);
      setError(err.message || 'Sunucuya bağlanılamadı! Backend çalışıyor mu?');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-[#050208] flex items-center justify-center px-6 relative overflow-hidden font-sans">
      <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[500px] h-[500px] bg-[#6b21a8] rounded-full blur-[150px] opacity-[0.10] pointer-events-none"></div>

      <div className="w-full max-w-md bg-white/[0.02] border border-white/10 backdrop-blur-2xl p-10 rounded-3xl z-10 shadow-2xl animate-[fadeInUp_0.5s_ease-out]">
        <div className="flex justify-center mb-4">
          <img src={myLogo} alt="Logo" className="h-12 object-contain drop-shadow-[0_0_15px_rgba(255,255,255,0.1)]" />
        </div>
        <p className="text-[#8c8496] text-sm text-center mb-8 font-light uppercase tracking-widest">Yönetici Paneli</p>

        {error && <div className="bg-red-500/10 border border-red-500/20 text-red-500 text-xs py-3 px-4 rounded-xl mb-6 text-center">{error}</div>}

        <form onSubmit={handleLogin} className="space-y-5">
          <div>
            <label className="text-xs text-[#7a7085] uppercase tracking-widest mb-2 block font-mono">Kullanıcı Adı</label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className="w-full bg-white/5 border border-white/10 rounded-xl py-3.5 px-5 text-white focus:outline-none focus:border-[#8b5cf6]/50 transition-all text-sm"
              placeholder="Kullanıcı adınız"
              required
              disabled={isLoading}
            />
          </div>
          <div>
            <label className="text-xs text-[#7a7085] uppercase tracking-widest mb-2 block font-mono">Şifre</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full bg-white/5 border border-white/10 rounded-xl py-3.5 px-5 text-white focus:outline-none focus:border-[#8b5cf6]/50 transition-all text-sm"
              placeholder="••••••••"
              required
              disabled={isLoading}
            />
          </div>
          <button
            type="submit"
            disabled={isLoading}
            className="w-full bg-white text-black font-bold py-4 rounded-xl hover:bg-gray-200 transition-all transform hover:-translate-y-1 mt-4 text-sm disabled:opacity-50 disabled:cursor-not-allowed"
          >
            {isLoading ? 'Giriş Yapılıyor...' : 'Giriş Yap'}
          </button>
        </form>
      </div>
    </div>
  );
}

export default Login;