import React, { useState, useEffect, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';

function Dashboard() {
  const [activeTab, setActiveTab] = useState('projects');
  const navigate = useNavigate();

  const [projects, setProjects] = useState([]);
  const [skills, setSkills] = useState([]);
  const [messages, setMessages] = useState([]);
  const [profile, setProfile] = useState({ title: '', subtitle: '', aboutText: '' });

  const [newProject, setNewProject] = useState({ title: '', description: '', technologies: '', duration: '', githubUrl: '', liveUrl: '' });
  const [newSkill, setNewSkill] = useState({ name: '', category: '', orderIndex: 0 });

  const fetchData = useCallback(async (endpoint, setter) => {
    try {
      const res = await fetch(`http://localhost:8081/api/v1/${endpoint}`);
      const result = await res.json();
      if (result.success) setter(result.data);
    } catch (err) { 
      console.error(`${endpoint} yükleme hatası:`, err); 
    }
  }, []);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token || token === "null") { navigate('/login'); } 
    else {
      fetchData('projects', setProjects);
      fetchData('skills', setSkills);
      fetchData('messages', setMessages);
      fetchData('profile', (data) => setProfile(data[0] || { title: '', subtitle: '', aboutText: '' }));
    }
  }, [navigate, fetchData]);

  const handleAdd = async (e, endpoint, body, resetFunc, refreshFunc) => {
    e.preventDefault();
    const token = localStorage.getItem('token');

    // --- VALIDATION KURALLARI ---
    if (endpoint === 'skills') {
      if (!body.name?.trim() || !body.category) {
        alert("Hata: Yetenek adı ve kategori seçimi zorunludur!");
        return;
      }
      const isDuplicate = skills.some(s => s.name?.toLowerCase() === body.name.trim().toLowerCase());
      if (isDuplicate) {
        alert("Hata: Bu yetenek zaten listenizde mevcut!");
        return;
      }
    }

    if (endpoint === 'projects' && !body.title?.trim()) {
      alert("Hata: Proje başlığı boş olamaz!");
      return;
    }

    try {
      const res = await fetch(`http://localhost:8081/api/v1/${endpoint}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${token}` },
        body: JSON.stringify(body)
      });
      if (res.ok) { 
        resetFunc(); 
        refreshFunc(); 
        alert("Başarıyla kaydedildi! ✨"); 
      }
    } catch (err) { 
      console.error("Ekleme hatası:", err); 
      alert("Bağlantı hatası!"); 
    }
  };

  const handleDelete = async (endpoint, id, refreshFunc) => {
    if (!window.confirm("Silmek istediğine emin misin?")) return;
    const token = localStorage.getItem('token');
    try {
      await fetch(`http://localhost:8081/api/v1/${endpoint}/${id}`, {
        method: 'DELETE',
        headers: { 'Authorization': `Bearer ${token}` }
      });
      refreshFunc();
    } catch (err) { console.error("Silme hatası:", err); }
  };

  return (
    <div className="min-h-screen bg-[#050208] text-[#E2DCE7] flex font-sans">
      {/* SIDEBAR */}
      <div className="w-64 bg-white/[0.02] border-r border-white/5 p-8 flex flex-col gap-8 shadow-2xl">
        <h1 className="text-2xl font-black tracking-tighter uppercase italic">Admin <span className="text-[#8b5cf6]">Panel</span></h1>
        <nav className="flex flex-col gap-2">
          {['projects', 'skills', 'messages', 'profile'].map(tab => (
            <button key={tab} onClick={() => setActiveTab(tab)} className={`text-left px-4 py-3 rounded-xl transition-all uppercase text-[10px] font-mono tracking-widest ${activeTab === tab ? 'bg-[#8b5cf6] text-white shadow-lg shadow-purple-500/20' : 'hover:bg-white/5 text-[#7a7085]'}`}>
              {tab === 'projects' ? '📁 Projeler' : tab === 'skills' ? '⚡ Yetenekler' : tab === 'messages' ? '📩 Mesajlar' : '👤 Profil'}
            </button>
          ))}
        </nav>
        <button onClick={() => { localStorage.removeItem('token'); navigate('/login'); }} className="mt-auto text-[10px] font-mono text-red-500 hover:bg-red-500/10 p-3 rounded-xl transition-all text-center">ÇIKIŞ YAP</button>
      </div>

      <div className="flex-1 p-12 overflow-y-auto h-screen scrollbar-hide">
        
        {/* YETENEKLER SEKİMESİ */}
        {activeTab === 'skills' && (
          <div className="grid grid-cols-1 lg:grid-cols-3 gap-12 animate-[fadeInUp_0.4s_ease-out]">
            <div className="lg:col-span-1 bg-white/[0.02] border border-white/10 p-8 rounded-3xl h-fit shadow-xl">
              <h2 className="text-lg font-bold mb-6 italic">Yeni Yetenek & Sıralama</h2>
              <form onSubmit={(e) => handleAdd(e, 'skills', newSkill, () => setNewSkill({name:'', category:'', orderIndex:0}), () => fetchData('skills', setSkills))} className="space-y-4">
                <input type="text" placeholder="Yetenek Adı" className="w-full bg-[#0A0510] border border-white/10 rounded-xl p-3 text-sm text-white focus:border-[#8b5cf6]/50 outline-none" value={newSkill.name} onChange={e => setNewSkill({...newSkill, name: e.target.value})} required />
                <select className="w-full bg-[#0A0510] border border-white/10 rounded-xl p-3 text-sm text-white focus:border-[#8b5cf6]/50 outline-none" value={newSkill.category} onChange={e => setNewSkill({...newSkill, category: e.target.value})} required>
                  <option value="" className="bg-[#050208]">Kategori Seç</option>
                  <option value="Backend" className="bg-[#050208]">Backend</option>
                  <option value="Frontend" className="bg-[#050208]">Frontend</option>
                  <option value="Mobil" className="bg-[#050208]">Mobil</option>
                  <option value="Genel" className="bg-[#050208]">Genel</option>
                </select>
                <input type="number" placeholder="Sıralama No" className="w-full bg-[#0A0510] border border-white/10 rounded-xl p-3 text-sm text-white" value={newSkill.orderIndex} onChange={e => setNewSkill({...newSkill, orderIndex: parseInt(e.target.value) || 0})} />
                <button type="submit" className="w-full bg-[#8b5cf6] py-4 rounded-xl font-bold shadow-lg transition-all hover:scale-[1.01]">Sisteme Ekle</button>
              </form>
            </div>
            <div className="lg:col-span-2 space-y-2">
              <h2 className="text-lg font-bold mb-4 italic text-[#F8F7F9]">Yayındaki Yetenekler</h2>
              {[...skills].sort((a,b) => (a.orderIndex || 0) - (b.orderIndex || 0)).map(s => (
                <div key={s.id} className="bg-white/[0.02] border border-white/5 p-4 rounded-xl flex justify-between items-center group hover:border-white/10">
                  <span className="text-sm font-medium italic"><span className="text-[#8b5cf6] font-mono mr-3">#{s.orderIndex}</span> {s.name} <small className="text-[#7a7085] ml-2">[{s.category || s.categoryName || 'Belirsiz'}]</small></span>
                  <button onClick={() => handleDelete('skills', s.id, () => fetchData('skills', setSkills))} className="text-red-500 opacity-0 group-hover:opacity-100 transition-all text-xs hover:underline uppercase">SİL</button>
                </div>
              ))}
            </div>
          </div>
        )}

        {/* PROJELER SEKİMESİ */}
        {activeTab === 'projects' && (
          <div className="grid grid-cols-1 lg:grid-cols-3 gap-12 animate-[fadeInUp_0.4s_ease-out]">
            <div className="lg:col-span-1 bg-white/[0.02] border border-white/10 p-8 rounded-3xl h-fit shadow-xl">
              <h2 className="text-lg font-bold mb-6 italic">Yeni Proje Yayınla</h2>
              <form onSubmit={(e) => handleAdd(e, 'projects', newProject, () => setNewProject({title:'', description:'', technologies:'', duration:'', githubUrl:'', liveUrl:''}), () => fetchData('projects', setProjects))} className="space-y-4">
                <input type="text" placeholder="Proje Başlığı" className="w-full bg-[#0A0510] border border-white/10 rounded-xl p-3 text-sm text-white focus:border-[#8b5cf6]/50 outline-none" value={newProject.title} onChange={e => setNewProject({...newProject, title: e.target.value})} required />
                <textarea placeholder="Detaylı Açıklama" className="w-full bg-[#0A0510] border border-white/10 rounded-xl p-3 text-sm text-white h-32 outline-none resize-none focus:border-[#8b5cf6]/50 transition-all" value={newProject.description} onChange={e => setNewProject({...newProject, description: e.target.value})} required></textarea>
                <input type="text" placeholder="Teknolojiler (virgülle)" className="w-full bg-[#0A0510] border border-white/10 rounded-xl p-3 text-sm text-white outline-none focus:border-[#8b5cf6]/50" value={newProject.technologies} onChange={e => setNewProject({...newProject, technologies: e.target.value})} />
                <button type="submit" className="w-full bg-[#8b5cf6] py-4 rounded-xl font-bold shadow-lg transition-all">Projeyi Kaydet</button>
              </form>
            </div>
            <div className="lg:col-span-2 space-y-4">
              <h2 className="text-lg font-bold mb-4 italic text-[#F8F7F9]">Projeler ({projects.length})</h2>
              {projects.map(p => (
                <div key={p.id} className="bg-white/[0.02] border border-white/5 p-6 rounded-2xl flex justify-between items-center group hover:border-white/20 transition-all">
                  <div><h3 className="font-bold text-[#F8F7F9]">{p.title}</h3><p className="text-xs text-[#7a7085] mt-1">{p.technologies}</p></div>
                  <button onClick={() => handleDelete('projects', p.id, () => fetchData('projects', setProjects))} className="p-3 bg-red-500/10 text-red-500 rounded-xl opacity-0 group-hover:opacity-100 transition-all hover:bg-red-500 hover:text-white uppercase text-xs">Sil</button>
                </div>
              ))}
            </div>
          </div>
        )}

        {/* MESAJLAR VE PROFİL AYNI MANTIKLA DEVAM EDER... */}
        {activeTab === 'messages' && (
          <div className="space-y-6 animate-[fadeInUp_0.4s_ease-out]">
            <h2 className="text-2xl font-bold mb-8 italic">Gelen Mesajlar ({messages.length})</h2>
            {messages.length === 0 ? <p className="text-[#50455e]">Henüz mesajınız yok.</p> : 
              messages.map(m => (
                <div key={m.id} className="bg-white/[0.02] border border-white/5 p-8 rounded-3xl relative group border-l-2 border-l-[#8b5cf6]">
                  <div className="flex justify-between items-start mb-4">
                    <div><h4 className="font-bold text-[#F8F7F9]">{m.name}</h4><p className="text-[#7a7085] text-xs">{m.email}</p></div>
                    <button onClick={() => handleDelete('messages', m.id, () => fetchData('messages', setMessages))} className="text-red-500 text-xs opacity-0 group-hover:opacity-100 transition-all">SİL</button>
                  </div>
                  <p className="text-[#928b9c] text-sm leading-relaxed italic">"{m.message}"</p>
                </div>
              ))
            }
          </div>
        )}

        {activeTab === 'profile' && (
          <div className="max-w-2xl bg-white/[0.02] border border-white/10 p-10 rounded-3xl animate-[fadeInUp_0.4s_ease-out]">
            <h2 className="text-xl font-bold mb-8 italic text-[#F8F7F9]">Site Metinlerini Güncelle</h2>
            <form onSubmit={(e) => handleAdd(e, 'profile', profile, () => {}, () => fetchData('profile', setProfile))} className="space-y-6">
              <div>
                <label className="text-[10px] uppercase text-[#7a7085] font-mono mb-2 block tracking-widest uppercase">Ana Sayfa Başlık</label>
                <input type="text" className="w-full bg-[#0A0510] border border-white/10 rounded-xl p-4 text-sm text-white focus:border-[#8b5cf6]/50 outline-none" value={profile.title} onChange={e => setProfile({...profile, title: e.target.value})} />
              </div>
              <div>
                <label className="text-[10px] uppercase text-[#7a7085] font-mono mb-2 block tracking-widest uppercase">Alt Başlık (Hakkımda)</label>
                <input type="text" className="w-full bg-[#0A0510] border border-white/10 rounded-xl p-4 text-sm text-white focus:border-[#8b5cf6]/50 outline-none" value={profile.subtitle} onChange={e => setProfile({...profile, subtitle: e.target.value})} />
              </div>
              <div>
                <label className="text-[10px] uppercase text-[#7a7085] font-mono mb-2 block tracking-widest uppercase">Hakkımda Metni</label>
                <textarea className="w-full bg-[#0A0510] border border-white/10 rounded-xl p-4 text-sm text-white h-40 outline-none resize-none focus:border-[#8b5cf6]/50 transition-all" value={profile.aboutText} onChange={e => setProfile({...profile, aboutText: e.target.value})}></textarea>
              </div>
              <button type="submit" className="w-full bg-[#8b5cf6] py-4 rounded-xl font-bold shadow-lg shadow-purple-500/20">Değişiklikleri Kaydet</button>
            </form>
          </div>
        )}
      </div>
    </div>
  );
}

export default Dashboard;