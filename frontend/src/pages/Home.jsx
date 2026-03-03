import React, { useState, useEffect } from 'react';

function Home() {
  const [isScrolled, setIsScrolled] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      setIsScrolled(window.scrollY > 50);
    };

    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  return (
    // 1. ZEMİN: Çok derin, soğuk bir mor-siyah (#050208). "overflow-hidden" ile taşmaları engelledik.
    <div className="min-h-screen bg-[#050208] text-[#E2DCE7] selection:bg-[#6b21a8] selection:text-white font-sans relative overflow-hidden">
      
      {/* 2. AMBİYANS IŞIKLARI (Svart'ın en büyük sırrı) 
          Arkada devasa, blurlu mor/indigo yuvarlaklar ile sahne ışığı veriyoruz. */}
      <div className="absolute top-[-20%] left-[-10%] w-[50%] h-[50%] bg-[#4c1d95] rounded-full mix-blend-screen filter blur-[180px] opacity-20 pointer-events-none"></div>
      <div className="absolute bottom-[10%] right-[-10%] w-[40%] h-[50%] bg-[#6b21a8] rounded-full mix-blend-screen filter blur-[180px] opacity-[0.15] pointer-events-none"></div>

      {/* --- ÜST MENÜ (HEADER) --- */}
      <header className="fixed top-0 left-0 right-0 z-50 flex justify-center pt-2 transition-all duration-1000">
        <nav 
          className={`relative flex items-center justify-between transition-all duration-[1200ms] ease-[cubic-bezier(0.16,1,0.3,1)]
          ${isScrolled 
            ? 'w-[90%] max-w-[850px] px-8 py-3.5 mt-4 rounded-full shadow-[0_0_30px_-10px_rgba(107,33,168,0.2)]' 
            : 'w-full max-w-7xl px-6 py-8 mt-0 rounded-none shadow-none'
          }`}
        >
          {/* Gezinen Işık (Menü arkası) */}
          <div className={`absolute inset-0 transition-opacity duration-1000 ${isScrolled ? 'opacity-100' : 'opacity-0'}`}>
            <div className="absolute top-1/2 left-1/2 w-[2000px] h-[2000px] -translate-x-1/2 -translate-y-1/2 bg-[conic-gradient(from_0deg,transparent_0_280deg,#8b5cf6_360deg)] opacity-30 animate-[spin_4s_linear_infinite]" />
            {/* Baloncuğun iç rengi: Ortama uygun siyah/mor karışımı */}
            <div className="absolute inset-[1px] bg-[#0A0510]/95 backdrop-blur-xl rounded-full" />
          </div>

          <div className="relative z-10 flex items-center justify-between w-full">
            <a href="#" className="text-2xl font-black tracking-tighter shrink-0 cursor-pointer text-white">
              YÜ.
            </a>

            <div className="hidden md:flex items-center space-x-10">
              <a href="#about" className="text-sm font-medium text-[#8c8496] hover:text-white transition-colors">Hakkımda</a>
              <a href="#skills" className="text-sm font-medium text-[#8c8496] hover:text-white transition-colors">Yetenekler</a>
              <a href="#projects" className="text-sm font-medium text-[#8c8496] hover:text-white transition-colors">Projeler</a>
            </div>

            <div className="flex items-center space-x-6 shrink-0">
              <div className="text-sm font-medium tracking-widest text-[#8c8496] hover:text-white transition-colors cursor-pointer flex items-center gap-2">
                <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M21 12a9 9 0 01-9 9m9-9a9 9 0 00-9-9m9 9H3m9 9a9 9 0 01-9-9m9 9c1.657 0 3-4.03 3-9s-1.343-9-3-9m0 18c-1.657 0-3-4.03-3-9s1.343-9 3-9m-9 9a9 9 0 019-9"></path></svg>
                TR <span className="text-[10px]">▼</span>
              </div>
              <a href="#contact" className="hidden sm:flex items-center gap-2 bg-white/5 hover:bg-white/10 border border-white/10 text-[#E2DCE7] text-sm font-medium py-2 px-6 rounded-full transition-all group backdrop-blur-sm">
                İletişim 
                <span className="transform group-hover:translate-x-1 group-hover:-translate-y-1 transition-transform text-white">↗</span>
              </a>
            </div>
          </div>
        </nav>
      </header>

      {/* --- 1. HERO (GİRİŞ) BÖLÜMÜ --- */}
      <section className="relative z-10 max-w-7xl mx-auto px-6 pt-40 md:pt-52 flex flex-col justify-center min-h-screen">
        <p className="text-[#8c8496] font-mono mb-6 tracking-widest uppercase text-xs md:text-sm">
          Yusuf Ülgen — Full Stack Developer
        </p>
        
        {/* Saf beyazdan kurtulduk, daha yumuşak bir kırık beyaz/gri tonu kullandık */}
        <h1 className="text-5xl md:text-7xl lg:text-[5.5rem] font-extrabold tracking-tight mb-8 leading-[1.05] text-[#F8F7F9]">
          Dijital dünyada <br />
          <span className="text-transparent bg-clip-text bg-gradient-to-r from-[#bca8ce] to-[#6d5b7e]">
            iz bırakan
          </span> çözümler.
        </h1>
        
        {/* Paragrafı çok daha yumuşak ve göz yormayan bir renge çektik */}
        <p className="text-[#928b9c] text-lg md:text-xl max-w-2xl mb-12 leading-relaxed font-light">
          Kusursuz çalışan sistemler ve göz alıcı arayüzler inşa ediyorum. Sadece kod yazmıyor, markanızı dijitalde bir adım öteye taşıyacak deneyimler tasarlıyorum.
        </p>
        
        <div className="flex flex-col sm:flex-row gap-5">
          {/* Svart tarzı Glassmorphism (Camsı) Buton. Göz yoran beyaz kütleden kurtulduk. */}
          <a href="#projects" className="group relative inline-flex items-center justify-center px-10 py-4 text-sm md:text-base font-semibold text-white transition-all duration-300 bg-white/[0.03] border border-white/10 rounded-full hover:bg-white/[0.08] hover:border-white/20 hover:shadow-[0_0_30px_rgba(168,85,247,0.15)] w-fit">
            Projelerimi Keşfet
          </a>
        </div>
      </section>

      {/* --- 2. HAKKIMDA BÖLÜMÜ --- */}
      <section id="about" className="relative z-10 max-w-7xl mx-auto px-6 py-32 md:py-48 flex flex-col md:flex-row items-center gap-16">
        
        <div className="flex-1">
          <h2 className="text-xs font-mono text-[#7a7085] tracking-[0.3em] uppercase mb-6">Hakkımda</h2>
          <h3 className="text-3xl md:text-4xl lg:text-5xl font-extrabold tracking-tighter leading-tight mb-8 text-[#F8F7F9]">
            Mantık ve Estetiğin <br /> Mükemmel Uyumu.
          </h3>
          <p className="text-[#928b9c] text-lg leading-relaxed font-light mb-8 max-w-xl">
            Backend mimarisinin o sarsılmaz ve güvenli yapısını kurarken, Frontend tarafında kullanıcının ekrana kilitlenmesini sağlayacak pürüzsüz arayüzler dokuyorum. Bir projenin sadece çalışması yetmez; hissettirmesi gerekir.
          </p>
          
          <a href="#skills" className="inline-flex items-center gap-4 text-[#d1c9d6] font-medium hover:text-white transition-colors group">
            Neler Yapabiliyorum
            <span className="w-10 h-[1px] bg-[#d1c9d6] group-hover:bg-white group-hover:w-16 transition-all duration-500"></span>
          </a>
        </div>

        <div className="flex-1 w-full relative">
          <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-64 h-64 bg-[#6b21a8] rounded-full blur-[120px] opacity-20 pointer-events-none"></div>
          
          <div className="relative grid grid-cols-2 gap-4">
            <div className="bg-white/[0.02] border border-white/5 p-8 rounded-2xl hover:border-white/10 transition-all duration-500 hover:-translate-y-2 cursor-default backdrop-blur-md">
              <div className="text-5xl font-black text-transparent bg-clip-text bg-gradient-to-b from-white to-[#50455e] mb-4">01</div>
              <div className="text-sm font-medium text-[#E2DCE7] mb-2">Backend Mimarisi</div>
              <div className="text-xs text-[#7a7085]">Java, Spring Boot, PostgreSQL</div>
            </div>
            
            <div className="bg-white/[0.02] border border-white/5 p-8 rounded-2xl hover:border-white/10 transition-all duration-500 hover:-translate-y-2 cursor-default translate-y-12 backdrop-blur-md">
              <div className="text-5xl font-black text-transparent bg-clip-text bg-gradient-to-b from-white to-[#50455e] mb-4">02</div>
              <div className="text-sm font-medium text-[#E2DCE7] mb-2">Frontend Arayüzü</div>
              <div className="text-xs text-[#7a7085]">React, Tailwind CSS, Vite</div>
            </div>
          </div>
        </div>
      </section>
      
      <div className="h-screen"></div>
    </div>
  );
}

export default Home;