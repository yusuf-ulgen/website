import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

// Sayfalarımızı içeri aktarıyoruz (Import)
import Home from './pages/Home';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Herkese açık ana sayfamız */}
        <Route path="/" element={<Home />} />
        
        {/* Senin giriş yapacağın sayfa */}
        <Route path="/login" element={<Login />} />
        
        {/* Senin yönetim panelin */}
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;